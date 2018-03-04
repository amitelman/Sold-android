package sold.monkeytech.com.sold_android.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import org.json.JSONObject;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.ui.custom_views.SoldProgressBar;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        FirebaseInstanceId.getInstance().getToken();
//        Intercom.initialize(getApplication(), getString(R.string.intercom_api_key), getString(R.string.intercom_app_id));

        initUi();


    }

    private void initUi() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((SoldProgressBar) findViewById(R.id.splashActivityPb)).isWithBkg(false).show();
            }
        }, 2000);

        if (TextUtils.isEmpty(UserManager.getInstance().getInAppToken())) {
            ((SoldProgressBar) findViewById(R.id.splashActivityPb)).isWithBkg(false).show();
            startLoginActivity();
        } else {
//            handleServerCall(new Action() {
//                @Override
//                public void execute() {
//                    //All Server call Success!
//                    Log.d("wowSplash", "success");
//                    if (!isLoginError)
//                        startMainActivity();
//                }
//            }, new Action() {
//                @Override
//                public void execute() {
//                    if (!isLoginError){
//                        Log.d("wowSplash", "login failed going to loginActivity");
//
//                        startLoginActivity();
//                    }
//                }
//            }, new Action() {
//                @Override
//                public void execute() {
//                    Log.d("wowSplash", "login failed");
//                    startMainActivity();
//                }
//            });
        }

    }

    public void startLoginActivity() {
//        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }

    public void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

//    private void handleServerCall(final Action onHandleDone, final Action onHandleFailed, final Action onError) {
//        final int[] i = {4};
//        Action onSuccess = new Action() {
//            @Override
//            public void execute() {
//                i[0]--;
//                if (i[0] == 0) {
//                    if (onHandleDone != null)
//                        onHandleDone.execute();
//                }
//            }
//        };
//        final int[] j = {0};
//        Action onFailed = new Action() {
//            @Override
//            public void execute() {
//                j[0]++;
//                Log.d("SplashActivity", "error has occurred");
//                if (j[0] == 1) {
//                    Log.d("wowSplash", "Splash error 1");
//                    if (onHandleFailed != null) {
//                        onHandleFailed.execute();
//                    }
//                }
////                if (j[0] == 2) {
////                    Log.d("wowSplash", "Splash error 2");
////                    isLoginError = true;
////                    if (onError != null)
////                        onError.execute();
////                }
//            }
//        };
//        initNewPagedQuery(onSuccess, onFailed);
//        getEnums(onSuccess, onFailed);
//        initDataFilter(onSuccess, onFailed);
//        getMe(onSuccess, onFailed);
//    }





}
