package sold.monkeytech.com.sold_android.framework.serverapi.abs.params;

import java.util.LinkedHashMap;

/**
 * Created by elitu on 8/28/14.
 */
public class TwoDArrayParam implements BaseParam<LinkedHashMap<String,String>> {
    private LinkedHashMap<String,String> value;
    private String addition;
    public TwoDArrayParam(LinkedHashMap<String,String> value,String addition){
        this.value=value;
        this.addition=addition;
    }
    @Override
    public LinkedHashMap<String,String> getValue() {
        return value;
    }
    public String getAddition(){return addition;};
}
