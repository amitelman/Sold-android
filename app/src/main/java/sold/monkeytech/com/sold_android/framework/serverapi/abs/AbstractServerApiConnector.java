package sold.monkeytech.com.sold_android.framework.serverapi.abs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.managers.BaseUserManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import sold.monkeytech.com.sold_android.SoldApplication;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ArrayParam;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.BaseParam;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.TextParam;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.TwoDArrayParam;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;

//import com.mid.mid.ui.activities.OAuth.OAuthActivity;


/**
 * Created by eli on 13/03/14.
 */

public class AbstractServerApiConnector {


    public static final String SERVER_URL = "http://sold-server-staging.herokuapp.com/api/v1";// FlavorConfig.SERVER_URL;
    private Context context;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private HttpClient httpClientSession = null;

    private  ServerAction serverAction;


    protected void setServerAction(Boolean isTokenRequired, ServerAction action){
        Log.d("wowSignup","setServerAction");
        this.serverAction = action;
        executeAndSignup(isTokenRequired);
    }

    protected void executeAndSignup(Boolean isTokenRequired){
        Log.d("wowSignup","executeAndSignup? - " + isTokenRequired);
        boolean shouldSignup = TextUtils.isEmpty(UserManager.getInstance().getInAppToken());
        if(isTokenRequired && shouldSignup){
            UserManager.getInstance().StartSignupFlow(context, new Action() {
                @Override
                public void execute() {
                    Log.d("wowSignup","executeAndSignup done -> server action");
                    serverAction.execute();
                }
            });
        }else{
            execute(new Runnable() {
                @Override
                public void run() {
                    serverAction.execute();
                }
            });
        }
    }

    public static void execute(final Runnable runnable) {
        executorService.execute(runnable);

    }

    public class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);
            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[]{tm}, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port,
                                   boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port,
                    autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }

    public AbstractServerApiConnector(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public synchronized HttpClient getHTTPClient() {
        if (httpClientSession == null) {
            try {
                HttpParams params = new BasicHttpParams();
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                ConnManagerParams.setMaxTotalConnections(params, 20);
                ConnManagerParams.setTimeout(params, 1000);
                ConnPerRoute cpr = new ConnPerRoute() {
                    @Override
                    public int getMaxForRoute(HttpRoute httpRoute) {
                        return 50;
                    }
                };
                ConnManagerParams.setMaxConnectionsPerRoute(params, cpr);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme("ftp", PlainSocketFactory.getSocketFactory(), 21));
                schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                schemeRegistry.register(new Scheme("https", sf, 443));
                ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
                httpClientSession = new DefaultHttpClient(cm, params);
            } catch (Exception e) {
                return new DefaultHttpClient();
            }
        }
        return httpClientSession;
    }

    public static String getBaseUrl() {
        return SERVER_URL;
//        return  "https://api.jobim.yad2.co.il/api/v1/";
    }

    protected String formatToDateToSend(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    public String convertStreamToSgetOutOfContextRemoteResponsetring(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    protected RemoteResponseString performHTTPPost(String url) {
        return this.performHTTPPost(true, url, new ParamBuilder());
    }

//    protected RemoteResponseString performHTTPPost(String url, ParamBuilder pm) {
//        return this.performHTTPPost(true, url, pm);
//    }

    protected RemoteResponseString performSpecificHTTPPost(String url) {
        return this.performHTTPPost(false, url, new ParamBuilder());
    }

    protected RemoteResponseString performHTTPPost(String url, ParamBuilder pm) {
        HttpPost httppost = new HttpPost(getBaseUrl() + url);

        if (UserManager.getInstance().getInAppToken() != null) {
            pm.addURLEncodedText("token", UserManager.getInstance().getInAppToken());
        }
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            HashMap<String, BaseParam> map = pm.build();
            for (String key : map.keySet()) {
                BaseParam val = map.get(key);
                if (val != null && val instanceof ArrayParam) {
                    if (((ArrayParam) val).getValue().isEmpty()) {
                        nameValuePairs.add(new BasicNameValuePair(key + "[]", ""));
                    } else {
                        for (int i = 0; i < ((ArrayParam) val).getValue().size(); i++)
                            nameValuePairs.add(new BasicNameValuePair(key + "[]", "" + ((ArrayParam) val).getValue().get(i)));
                    }
                } else if (val != null && val instanceof TextParam) {
                    String value = ((TextParam) val).getValue();
                    if (value != null)
                        nameValuePairs.add(new BasicNameValuePair(key, "" + value));
                } else if (val != null && val instanceof TwoDArrayParam) {
                    HashMap<String, String> value = ((TwoDArrayParam) val).getValue();
                    Iterator it = value.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        nameValuePairs.add(new BasicNameValuePair(key + "[][" + pair.getKey() + "]", "" + pair.getValue()));
                        it.remove(); // avoids a ConcurrentModificationException
                    }
                }
            }

            // Execute HTTP Post Request
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = getHTTPClient().execute(httppost);
            String strResponse = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            Log.d("HTTPsss", url + "[POST] Response : " + response.getStatusLine().getStatusCode());
            if (context != null) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED)
                    return new RemoteResponseString(true, strResponse, response.getStatusLine().getStatusCode());
                else
                    return new RemoteResponseString(false, strResponse, response.getStatusLine().getStatusCode());
            } else
                return getOutOfContextRemoteResponse();
        } catch (Exception e) {
            Log.d("HTTPsss", "Error on " + url + " : " + e.getMessage());
            if (context != null)
                return new RemoteResponseString(false, e.getMessage(), 0);
            else
                return getOutOfContextRemoteResponse();
        }

    }

    protected RemoteResponseString performHTTPPost(boolean shouldUseBase, String url, ParamBuilder pm) {
        Handler handler = new Handler(Looper.getMainLooper());
        HttpPost httppost = new HttpPost((shouldUseBase ? getBaseUrl() : "") + url);

//        if (UserManager.getInstance().getInAppToken() != null) {
//            String keyWord = UserManager.getInstance().getAppKeyWord();
//            String token = UserManager.getInstance().getInAppToken();
//            httppost.addHeader("Authorization", keyWord + " " + token);
//            httppost.addHeader("Content-Type", "application/json");
//        }
        // Execute HTTP Post Request

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        try {
            HashMap<String, BaseParam> map = pm.build();
            for (String key : map.keySet()) {
                BaseParam val = map.get(key);
                if (val != null && val instanceof TextParam) {
                    String value = ((TextParam) val).getValue();
                    if (value != null)
                        nameValuePairs.add(new BasicNameValuePair(key, "" + value));
                }
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
//        if (UserManager.getInstance().getInAppToken() != null) {
//            params = params + (params.isEmpty()?"?":"&") + UserManager.getInstance().getInAppTokenKey() + "=" + UserManager.getInstance().getInAppToken();
//        }


        HttpResponse response = null;
        String strResponse = "";
        try {
            response = getHTTPClient().execute(httppost);
            if (response.getEntity() != null) {
                strResponse = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            } else {
                strResponse = "";
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Log.d("HTTPsss", url + "[POST] Response : " + response.getStatusLine().getStatusCode());
        if (context != null) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED || response.getStatusLine().getStatusCode() == 204) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            new Error500Dialog(context).show();
//                        }
//                    });
                return new RemoteResponseString(true, strResponse, response.getStatusLine().getStatusCode());
            } else {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Log.d("wowServer", "500 Post Error!!!!");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            new Error500Dialog(context).show();
                        }
                    });
                }
                if (response.getStatusLine().getStatusCode() == 401) { //IN_APP_TOKEN IS NOT VALID ANYMORE !!!!

                }
                return new RemoteResponseString(false, strResponse, response.getStatusLine().getStatusCode());
            }
        } else
            return getOutOfContextRemoteResponse();
    }

    private RemoteResponseString getOutOfContextRemoteResponse() {
        return new RemoteResponseString(false, "Out of context", 0);
    }

    protected RemoteResponseString performHTTPGet(String url) {
        return this.performHTTPGet(url, new ParamBuilder());
    }

    protected RemoteResponseString performHTTPGet(String url, ParamBuilder pm) {
        return performHTTPGet(url, pm, true);
    }

    protected RemoteResponseString performHTTPGet(String url, ParamBuilder pm, boolean useBase) {
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            HttpResponse response = null;
            InputStream content = null;
            String params = "";
            if(pm != null) {
                HashMap<String, BaseParam> map = pm.build();

                for (String key : map.keySet()) {
                    BaseParam val = map.get(key);
                    if (val != null && val instanceof TextParam) {
                        String value = ((TextParam) val).getValue();
                        params += (params.isEmpty() ? "?" : "&") + key + "=" + value;
                    }
                    if (val != null && val instanceof ArrayParam) {
                        List<String> value = ((ArrayParam) val).getValue();
                        for (String v : value)
                            params += (params.isEmpty() ? "?" : "&") + key + "[]=" + v;
                    }
                }
            }

            if (UserManager.getInstance().getInAppToken() != null) {
                params = params + (params.isEmpty()?"?":"&") + UserManager.getInstance().getInAppTokenKey() + "=" + UserManager.getInstance().getInAppToken();
            }
            HttpGet httpGet = new HttpGet((useBase ? getBaseUrl() : "") + url + params);
//            if (UserManager.getInstance().getInAppToken() != null) {
//                pm.addURLEncodedText("token", UserManager.getInstance().getInAppToken());
//            }
            response = getHTTPClient().execute(httpGet);
//            response = getHTTPClient().execute(new HttpGet((useBase ? getBaseUrl() : "") + url + params));

            //content = response.getEntity().getContent();
            String strResponse = (response.getEntity() == null) ? "" : EntityUtils.toString(response.getEntity(), HTTP.UTF_8); //convertStreamToString(content);
            Log.d("HTTPsss", "[GET] " + (useBase ? getBaseUrl() : "") + url + params + "  Response : " + response.getStatusLine().getStatusCode());
            if (context != null) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED || response.getStatusLine().getStatusCode() == 204)
                    return new RemoteResponseString(true, strResponse, response.getStatusLine().getStatusCode());
                else {
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Log.d("wowServer", "500 Error!!!!");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                new Error500Dialog(context).show();
                            }
                        });
                    }
                    return new RemoteResponseString(false, strResponse, response.getStatusLine().getStatusCode());
                }
            } else
                return getOutOfContextRemoteResponse();
        } catch (Exception e) {
            Log.d("HTTPsss", "Error on " + url + " : " + e.getMessage());
            if (context != null)
                return new RemoteResponseString(false, e.getMessage(), 0);
            else
                return getOutOfContextRemoteResponse();
        }
    }

    protected RemoteResponseString performHTTPDelete(String url, ParamBuilder pm) {
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            HttpResponse response = null;
            InputStream content = null;
//            url += "?token=" + UserManager.getInstance().getInAppToken();
            String params = "";
            if(pm != null) {
                HashMap<String, BaseParam> map = pm.build();

                for (String key : map.keySet()) {
                    BaseParam val = map.get(key);
                    if (val != null && val instanceof TextParam) {
                        String value = ((TextParam) val).getValue();
                        params += (params.isEmpty() ? "?" : "&") + key + "=" + value;
                    }
                    if (val != null && val instanceof ArrayParam) {
                        List<String> value = ((ArrayParam) val).getValue();
                        for (String v : value)
                            params += (params.isEmpty() ? "?" : "&") + key + "[]=" + v;
                    }
                }
            }
            if (UserManager.getInstance().getInAppToken() != null) {
                params = params + (params.isEmpty()?"?":"&") + UserManager.getInstance().getInAppTokenKey() + "=" + UserManager.getInstance().getInAppToken();
            }
            HttpDelete httpDelete = new HttpDelete(getBaseUrl() + url + params);

            response = getHTTPClient().execute(httpDelete);
            String strResponse = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            Log.d("HTTPsss", url + "[DELETE] Response : " + response.getStatusLine().getStatusCode());
            if (context != null) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                    return new RemoteResponseString(true, strResponse, response.getStatusLine().getStatusCode());
                else {
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        Log.d("wowServer", "500 Error!!!!");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                new Error500Dialog(context).show();
                            }
                        });
                    }
                    return new RemoteResponseString(false, strResponse, response.getStatusLine().getStatusCode());
                }
            } else
                return getOutOfContextRemoteResponse();
        } catch (Exception e) {
            Log.d("HTTPsss", "Error on " + url + " : " + e.getMessage());
            if (context != null) {
                return new RemoteResponseString(false, e.getMessage(), 0);
            } else
                return getOutOfContextRemoteResponse();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) SoldApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

