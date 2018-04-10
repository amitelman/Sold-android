package sold.monkeytech.com.sold_android.framework.managers;

import android.content.Context;
import android.os.Handler;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;
import com.pixplicity.easyprefs.library.Prefs;

import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.ui.activities.PropertyPageActivity;
import sold.monkeytech.com.sold_android.ui.dialogs.LoginDialog;
import sold.monkeytech.com.sold_android.ui.dialogs.SignupDialog;
import sold.monkeytech.com.sold_android.ui.dialogs.VerificationDialog;


/**
 * Created by MonkeyFather on 20/08/2017.
 */

public class UserManager {

    private static final String IN_APP_TOKEN_PREFERENCE_KEY = "inAppToken";
    private static final String IN_APP_KEY_WORD = "inAppKeyWord";

    public static UserManager instance;
    private String inAppToken;
    private String appKeyWord;
    private String metaData;
    private User currentUser;



    public static UserManager getInstance(){
        if(instance == null)
            instance = new UserManager();
        return instance;
    }



    public String getInAppToken(){
        return Prefs.getString(IN_APP_TOKEN_PREFERENCE_KEY,"");
    }

    public void setInAppToken(String inAppToken) {
        this.inAppToken = inAppToken;
        Prefs.putString(IN_APP_TOKEN_PREFERENCE_KEY, inAppToken);
    }

    public void setAppKeyWord(String appKeyWord) {
        this.appKeyWord = appKeyWord;
        Prefs.putString(IN_APP_KEY_WORD, appKeyWord);
    }

    public String getAppKeyWord() {
        if(appKeyWord == null)
            appKeyWord = Prefs.getString(IN_APP_KEY_WORD, "");
        return appKeyWord;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getMetaData() {
        return metaData;
    }

    public String getInAppTokenKey() {
        return "token";
    }

    public void StartSignupFlow(Context context, Action onSignupFlowDoneAction) {
        new SignupDialog(context, getValidationAction(context, onSignupFlowDoneAction), onLoginBtnPressed(context, getValidationAction(context, onSignupFlowDoneAction))).show();
    }

    private Action onLoginBtnPressed(final Context context, final TAction<String> onSignupFlowDoneAction) {
        return new Action() {
            @Override
            public void execute() {
                new LoginDialog(context, onSignupFlowDoneAction).show();
            }
        };
    }

    public TAction<String> getValidationAction(final Context context, final Action onSignupFlowDoneAction) {
        return new TAction<String>() {
            @Override
            public void execute(String phone) {
                new VerificationDialog(context, phone, onSignupFlowDoneAction).show();
            }
        };
    }

    public Action getValidationDialog(final Context context, final String phone, final Action onValidateSuccess){
        final Handler handler = new Handler();
        return new Action() {
            @Override
            public void execute() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        new VerificationDialog(context, phone, onValidateSuccess).show();
                    }
                });
            }
        };
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }


}

