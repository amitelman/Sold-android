package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.SavedSearch;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class SavedSearchParser extends GeneralParser<SavedSearch> {

    public SavedSearchParser() {
        super();
    }

    @Override
    public SavedSearch parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        SavedSearch search = null;
        search = getObjectFromCache(jo,"id");
        if(search == null){
            search = new SavedSearch();
            search.setId(safeParseLong(jo, "id"));
        }

        search.setName(safeParseString(jo, "name"));
        search.setLat(safeParseString(jo, "lat"));
        search.setLng(safeParseString(jo, "lng"));
        search.setMinFloorArea(safeParseInt(jo, "min_floor_area"));
        search.setMaxFloorArea(safeParseInt(jo, "max_floor_area"));
        search.setMinPlotArea(safeParseInt(jo, "min_plot_area"));
        search.setMaxPlotArea(safeParseInt(jo, "max_plot_area"));
        search.setMinFloors(safeParseInt(jo, "min_floor"));
        search.setMaxFloors(safeParseInt(jo, "max_floor"));
        search.setMinRooms(safeParseInt(jo, "min_rooms_count"));
        search.setMinBaths(safeParseInt(jo, "min_bathrooms_count"));
        search.setHasParking(safeParseBoolean(jo, "has_parking"));
        search.setHasOpenHouse(safeParseBoolean(jo, "has_open_house"));
        search.setHideForeclosures(safeParseBoolean(jo, "hide_foreclosures"));
        search.setHideNewConstruction(safeParseBoolean(jo, "hide_new_construction"));
        search.setPropertyType(new PropertyTypeParser().parseToList(jo.getJSONArray("property_types")));
        search.setFeatures(new PropertyFeaturesParser().parseToList(jo.getJSONArray("features")));
        search.setMinPrice(new PriceParser().parseToSingle(jo.getJSONObject("min_price")));
        search.setMaxPrice(new PriceParser().parseToSingle(jo.getJSONObject("max_price")));
        search.setCreatedAt(safeParseString(jo, "created_at"));

        return search;
    }

    @Override
    protected Class getType() {
        return SavedSearch.class;
    }
}

