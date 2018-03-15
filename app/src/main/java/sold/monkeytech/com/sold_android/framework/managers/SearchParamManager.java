package sold.monkeytech.com.sold_android.framework.managers;

import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;

/**
 * Created by MonkeyFather on 15/03/2018.
 */

public class SearchParamManager {

    public static SearchParamManager instance;
    ParamBuilder paramBuilder;

    public static SearchParamManager getInstance(){
        if(instance == null){
            instance = new SearchParamManager();
        }
        return instance;
    }

    public ParamBuilder getParams(){
        if (paramBuilder == null){
            paramBuilder = new ParamBuilder();
            paramBuilder.addDouble("lat",32.0853); //TelAviv LatLng
            paramBuilder.addDouble("lng",34.7818);
        }
        return paramBuilder;
    }

    public void updateParams(String key, String value){
        if(paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addText(key, value);
    }

    public void updateParams(String key, int value){
        if(paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addInt(key, value);
    }

    public void updateParams(String key, Double value){
        if(paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addDouble(key, value);
    }

    public void updateParams(String key, boolean value){
        if(paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addBoolean(key, value);
    }

    public void clearParams(){
        paramBuilder = null;
    }

}
