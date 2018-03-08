package sold.monkeytech.com.sold_android.framework.models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class PropertyFeatures {

    private String featureName;
    private List<Meta> meta;
    private List<String> images;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public List<Meta> getMeta() {
        return meta;
    }

    public void setMeta(List<Meta> meta) {
        this.meta = meta;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
