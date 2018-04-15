package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiSaveSearch extends AbstractServerApiConnector {

    public ApiSaveSearch(Context context) {
        super(context);
    }

    public synchronized void request(final String name, final double lat, final double lng, final String typeCsv, final String featureCsv, final int minFloorArea, final int maxFloorArea,
                                     final int minPlotArea, final int maxPlotArea, final int minPrice, final int maxPrice, final int minFloor, final int maxFloor, final int minRooms, final int minBath,
                                     final boolean hasOpenHouse, final boolean hideForeclosure, final boolean hasParking, final boolean hideNewConstruction,
                                     final Action onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                params.addText("name", name)
                        .addDouble("lat", lat)
                        .addDouble("lng", lng)
                        .addText("property_type_ids", typeCsv)
                        .addText("feature_ids", featureCsv)
                        .addInt("min_floor_area", minFloorArea)
                        .addInt("max_floor_area", maxFloorArea)
                        .addInt("min_plot_area", minPlotArea)
                        .addInt("max_plot_area", maxPlotArea)
                        .addInt("min_price", minPrice)
                        .addInt("max_price", maxPrice)
                        .addInt("min_floor", minFloor)
                        .addInt("max_floor", maxFloor)
                        .addInt("min_rooms_count", minRooms)
                        .addInt("min_bathrooms_count", minBath)
                        .addBoolean("has_open_house", hasOpenHouse)
                        .addBoolean("hide_foreclosures", hideForeclosure)
                        .addBoolean("has_parking", hasParking)
                        .addBoolean("hide_new_construction", hideNewConstruction);

                RemoteResponseString remoteResponseString = performHTTPPost("/users/me/searches", params);
                if (remoteResponseString.isSuccess()) {
                    if (onSuccess != null)
                        onSuccess.execute();
                } else {
                    if (onFail != null)
                        onFail.execute();
                }
            }
        });
    }
}