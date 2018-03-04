//package sold.monkeytech.com.sold_android.framework.serverapi.user;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Handler;
//import android.os.Looper;
//
//import com.monkeytechy.framework.interfaces.Action;
//import com.monkeytechy.framework.interfaces.TAction;
//
//import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
//
//import static com.mid.mid.FlavorConfig.BASE_LOGIN;
//
////import com.mid.mid.ui.activities.OAuth.OAuthActivity;
//
///**
// * Created by monkey on 08/06/2015.
// */
//public class ApiLogout extends AbstractServerApiConnector {
//
//    private final Activity activity;
//    private Context context;
//
//    public ApiLogout(Activity activity, Context context) {
//        super(context);
//        this.context = context;
//        this.activity = activity;
//    }
//
//    public synchronized void request(final Action onSuccess, final TAction<String> onFail) {
//
////        OauthLogoutActivty.start(activity, new Action() {
////            @Override
////            public void execute() {
////                if(onSuccess != null){
////                    UserManager.getInstance().clearUser();
////                    onSuccess.execute();
////                }
////            }
////        });
//
////        String url = "https://preprod-login.midonline.com/core/connect/endsession";
//        String url = BASE_LOGIN + "core/connect/endsession?id_token_hint=" + UserManager.getInstance().getIdToken() + "&post_logout_redirect_uri=" + FlavorConfig.REDIRECT_URL;
////
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        context.startActivity(intent);
////
////        if(onSuccess != null){
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                UserManager.getInstance().clearUser();
//                onSuccess.execute();
//            }
//        },1000);
////                }
////        AndroidNetworking.get(url)
////                            .build()
////                            .getAsString(new StringRequestListener() {
////                                @Override
////                                public void onResponse(String response) {
////                                    Log.d("wow","LOGOUT TRUE");
////                                    Intent intent = new Intent(context, LoginActivity.class);
////                                    context.startActivity(intent);
////                                }
////
////                                @Override
////                                public void onError(ANError anError) {
////                                    Log.d("wow","LOGOUT FALSE");
////                                }
////                            });
//                }
//
////        Intent i = new Intent(Intent.ACTION_VIEW);
////        i.setData(Uri.parse(url));
////        context.startActivity(i);
//
////        if(onSuccess != null)
////            onSuccess.execute();
////        OauthLogoutActivty.start(activity, new Action() {
////            @Override
////            public void execute() {
////                if(onSuccess != null){
////                    UserManager.getInstance().clearUser();
////                    onSuccess.execute();
////                }
////            }
////        });
////
//
////        String url = "https://preprod-login.midonline.com/core/connect/endsession";
////        Log.d("wowLogout","logout url: " + url);
////        Intent i = new Intent(Intent.ACTION_VIEW);
////        i.setData(Uri.parse(url));
////        context.startActivity(i);
////
////        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
////            @Override
////            public void run() {
////
////
////            }
////        }, 1250);
//
//}
