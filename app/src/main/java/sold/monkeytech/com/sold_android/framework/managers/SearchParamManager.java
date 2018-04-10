package sold.monkeytech.com.sold_android.framework.managers;

import com.google.android.gms.maps.model.LatLng;
import com.pixplicity.easyprefs.library.Prefs;

import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;

/**
 * Created by MonkeyFather on 15/03/2018.
 */

public class SearchParamManager {

    private static final String SORT_ID = "sortId";
    private static final String SORT_DIRECTION = "sortDirection";

    public static SearchParamManager instance;
    ParamBuilder paramBuilder;

    private long sortId;
    private String sortDirection;
    private LatLng lastSearchLatLng;

    public static SearchParamManager getInstance(){
        if(instance == null){
            instance = new SearchParamManager();
        }
        return instance;
    }

    public ParamBuilder getParams(){
        if (paramBuilder == null){
            paramBuilder = new ParamBuilder();
            paramBuilder.addDouble("lat",10.0853); //TelAviv LatLng
            paramBuilder.addDouble("lng",10.7818);

            paramBuilder.addInt("sortable_id", getSortId());
            paramBuilder.addText("sort_direction", getSortDirection());
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

    public void setSortId(long sortId) {
        this.sortId = sortId;
        Prefs.putInt(SORT_ID, (int) sortId);
    }

    public int getSortId() {
        return Prefs.getInt(SORT_ID, 0);
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
        Prefs.putString(SORT_DIRECTION, sortDirection);
    }

    public String getSortDirection() {
        return Prefs.getString(SORT_DIRECTION, "");
    }

    public void setLastSearchLatLng(LatLng lastSearchLatLng) {
        this.lastSearchLatLng = lastSearchLatLng;
    }

    public LatLng getLastSearchLatLng() {
        return lastSearchLatLng;
    }
}
