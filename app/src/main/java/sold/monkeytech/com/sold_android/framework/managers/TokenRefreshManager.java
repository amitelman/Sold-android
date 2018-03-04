//package sold.monkeytech.com.sold_android.framework.managers;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import com.androidnetworking.AndroidNetworking;
//import com.androidnetworking.error.ANError;
//import com.androidnetworking.interfaces.JSONObjectRequestListener;
//import com.mid.mid.FlavorConfig;
//import com.mid.mid.framework.Utils.TextUtils;
//import com.mid.mid.ui.activities.OAuth.LoginActivity;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by MonkeyFather on 28/02/2018.
// */
//
//public class TokenRefreshManager {
//    public static final String REDIRECT_URL = FlavorConfig.REDIRECT_URL;
//
//    public interface OnTokenRefreshListener {
//        void onRefresh(boolean isSuccess);
//    }
//
//    public static void refreshToken(Context context, final OnTokenRefreshListener onTokenRefreshListener) {
//        if (!TextUtils.isEmpty(UserManager.getInstance().getRefreshToken())) {
//            Log.d("wowAuth", "refreshing token start");
//            AndroidNetworking.post(FlavorConfig.BASE_TOKEN)
//                    .addBodyParameter("redirect_uri", REDIRECT_URL)
//                    .addBodyParameter("refresh_token", UserManager.getInstance().getRefreshToken())
//                    .addBodyParameter("grant_type", "refresh_token")
//                    .addHeaders("Authorization", "Basic bW9iaWxlYXBpMmZyb250ZW5kOm1pZHNlY3JldA==")
//                    .build()
//                    .getAsJSONObject(new JSONObjectRequestListener() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            //result for new inAppToken and new RefreshToken
//                            UserManager.getInstance().setIsTokenRefreshing(false);
//                            Log.d("wowAuth", "b TOKEN  RESPONSE (GOOD) : " + response.toString());
//                            try {
//                                String inAppToken = response.getString("access_token");
//                                int expireIn = response.getInt("expires_in");
//                                String refreshToken = response.getString("refresh_token");
//                                UserManager.getInstance().setInAppToken(inAppToken);
//                                Log.d("wowAuth", "new in app token: " + inAppToken);
//                                UserManager.getInstance().setTokenExpiresIn(expireIn);
//                                UserManager.getInstance().setRefreshToken(refreshToken);
//
//                                if (onTokenRefreshListener != null) {
//                                    onTokenRefreshListener.onRefresh(true);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                if (onTokenRefreshListener != null) {
//                                    onTokenRefreshListener.onRefresh(false);
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onError(ANError error) {
//                            UserManager.getInstance().setIsTokenRefreshing(false);
//                            Log.d("wowAuth", "b TOKEN RESPONSE (BAD) :  " + error.getErrorBody());
//                        }
//                    });
//        } else {
//            if (!TextUtils.isEmpty(UserManager.getInstance().getInAppToken()) && UserManager.getInstance().shouldRefreshToken())
////                refreshToken(context, onTokenRefreshListener);
////            else {
//                Log.d("wowAuth", "refreshToken error");
//                Intent intent = new Intent(context, LoginActivity.class);
//                context.startActivity(intent);
//
////            }
//        }
//    }
//}
