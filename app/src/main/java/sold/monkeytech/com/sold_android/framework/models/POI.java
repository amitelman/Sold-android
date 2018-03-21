package sold.monkeytech.com.sold_android.framework.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import sold.monkeytech.com.sold_android.framework.cache.POICache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class POI extends BaseModel<POI> {

    private String name;
    private String description;
    private String address;
    private String lat;
    private String lng;
    private int rating;
    private List<Meta> meta;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Meta> getMeta() {
        return meta;
    }

    public void setMeta(List<Meta> meta) {
        this.meta = meta;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static ArrayList<Category> getSortedCategories(List<Category> categories){
        Set set = new TreeSet(new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                if(o1.getId() == (o2.getId())){
                    return 0;
                }
                return 1;
            }
        });
        set.addAll(categories);

        return new ArrayList<>(set);
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return POICache.getInstance();
    }

    @Override
    protected Class getType() {
        return POI.class;
    }
}
