package sold.monkeytech.com.sold_android.framework.managers;

import com.pixplicity.easyprefs.library.Prefs;


/**
 * Created by MonkeyFather on 20/08/2017.
 */

public class UserManager {

    private static final String IN_APP_TOKEN_PREFERENCE_KEY = "inAppToken";
    private static final String IN_APP_KEY_WORD = "inAppKeyWord";

    public static UserManager instance;
    private String inAppToken;
    private String appKeyWord;


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

}

