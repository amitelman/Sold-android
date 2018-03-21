package sold.monkeytech.com.sold_android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.ui.activities.BaseActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Locale;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.serverapi.utils.ApiGetMetaData;
import sold.monkeytech.com.sold_android.ui.custom_views.SoldProgressBar;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        FirebaseInstanceId.getInstance().getToken();
//        Intercom.initialize(getApplication(), getString(R.string.intercom_api_key), getString(R.string.intercom_app_id));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startMainActivity();
//            }
//        },1000);
        initUi();


    }

    private void checkLang() {
        Locale locale = Locale.getDefault();
        String lang = Prefs.getString("app_lang",locale.toString());
        if (!lang.equals("iw") && !lang.equals("en"))
            lang = "en";
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Prefs.putString("app_lang",lang);
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
            handleServerCall(onHandlerSuccess(), onHandlerFailed());

        } else {
        }

    }

    private Action onHandlerSuccess() {
        return new Action() {
            @Override
            public void execute() {
                Log.d("wowSplash","Handle server Success");
                startMainActivity();
            }
        };
    }

    private Action onHandlerFailed() {
        return new Action() {
            @Override
            public void execute() {
                //todo: what to do in case handle server error
                Log.d("wowSplash","Handle server failed");
                startMainActivity();
            }
        };
    }

    public void startLoginActivity() {
//        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }

    public void startMainActivity() {
//        Intent intent = new Intent(SplashActivity.this, FilterSearchActivity.class);
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleServerCall(final Action onHandleDone, final Action onError) {
        final int[] i = {1};
        Action onSuccess = new Action() {
            @Override
            public void execute() {
                i[0]--;
                if (i[0] == 0) {
                    if (onHandleDone != null)
                        onHandleDone.execute();
                }
            }
        };
        final int[] j = {0};
        Action onFailed = new Action() {
            @Override
            public void execute() {
                j[0]++;
                Log.d("SplashActivity", "error has occurred");
                if (j[0] == 1) {
                    Log.d("wowSplash", "Splash error 1");
                    if (onError != null) {
                        onError.execute();
                    }
                }
            }
        };
        metaData(onSuccess, onFailed);
//        getEnums(onSuccess, onFailed);
//        initDataFilter(onSuccess, onFailed);
//        getMe(onSuccess, onFailed);
    }

    private void metaData(Action onSuccess, Action onFailed) {
        new ApiGetMetaData(this).request(onSuccess, onFailed);
    }


}
