package sold.monkeytech.com.sold_android.framework.serverapi.abs.params;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by elitu on 8/28/14.
 */
public class ParamBuilder {
    private boolean isMultipart=false;

    HashMap<String,BaseParam> map = new HashMap<String, BaseParam>();
    private HashMap<String,Integer> arrayCounters= new HashMap<>();

    public ParamBuilder addText(String key , String value){
        map.put(key , new TextParam(value));
        return this;
    }
    public ParamBuilder addArray(String key , List<String> value) {
        map.put(key, new ArrayParam(value));
        return this;
    }

    public ParamBuilder add2DArray(String key , LinkedHashMap<String,String> value) {
        int i=0;
        if (arrayCounters.keySet().contains(key))
            i=arrayCounters.get(key)+1;
        arrayCounters.put(key,i);
        map.put(key+"@"+i, new TwoDArrayParam(value,"@"+i));
        return this;
    }

    public ParamBuilder addURLEncodedText(String key , String value){
        try {
            map.put(key , new TextParam(URLEncoder.encode(value,"UTF-8")));
        } catch (UnsupportedEncodingException e) {
        }
        return this;
    }
    public ParamBuilder addDouble(String key , Double value){
       return addText(key,""+value);
    }
    public ParamBuilder addInt(String key , Integer value){
        return addText(key,value!=null?""+value:null);
    }
    public ParamBuilder addLong(String key , Long value){
        return addText(key,""+value);
    }
    public ParamBuilder addDate(String key , Date value){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return addText(key,""+dateFormat.format(value));
    }
    public ParamBuilder addBoolean(String key , Boolean value){
        return addText(key,""+value);
    }
    public ParamBuilder addFile(String key , String path){
        isMultipart=true;
        map.put(key , new PicParam(path));
        return this;
    }

    public void removeParam(String key){

        try {
            map.remove(key);
        }catch (Exception e){}

    }

    public boolean isMultipart() {
        return isMultipart;
    }
    public HashMap<String,BaseParam> build() {
        return map;
    }
}
