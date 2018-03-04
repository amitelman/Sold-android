package sold.monkeytech.com.sold_android.framework.serverapi.abs;

import com.monkeytechy.framework.interfaces.Action;

/**
 * Created by MonkeyFather on 25/02/2018.
 */

public class ServerAction {
    //AbstractServerApiConnector serverConnector;
    Action action;
    public ServerAction(Action action){
        this.action = action;
    }
    public void execute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (action!=null)
                    action.execute();
            }
        }).start();
    }

}
