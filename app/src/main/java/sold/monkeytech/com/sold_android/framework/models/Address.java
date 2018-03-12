package sold.monkeytech.com.sold_android.framework.models;

import sold.monkeytech.com.sold_android.framework.cache.AddressCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class Address extends BaseModel {

    private Long streetId;
    private Long cityId;
    private String streetName;
    private String cityName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return AddressCache.getInstance();
    }

    @Override
    protected Class getType() {
        return Address.class;
    }
}
