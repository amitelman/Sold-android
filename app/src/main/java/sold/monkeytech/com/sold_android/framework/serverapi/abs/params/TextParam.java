package sold.monkeytech.com.sold_android.framework.serverapi.abs.params;

/**
 * Created by elitu on 8/28/14.
 */
public class TextParam implements BaseParam<String> {
    private String value;
    public TextParam(String value){
        this.value=value;
    }
    @Override
    public String getValue() {
        return value;
    }
}
