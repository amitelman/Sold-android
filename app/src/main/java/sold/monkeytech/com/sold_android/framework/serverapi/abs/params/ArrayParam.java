package sold.monkeytech.com.sold_android.framework.serverapi.abs.params;

import java.util.List;

/**
 * Created by elitu on 8/28/14.
 */
public class ArrayParam implements BaseParam<List<String>> {
    private List<String> value;
    public ArrayParam(List<String> value){
        this.value=value;
    }
    @Override
    public List<String> getValue() {
        return value;
    }
}
