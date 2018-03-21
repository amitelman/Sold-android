package sold.monkeytech.com.sold_android.framework.models;


import sold.monkeytech.com.sold_android.framework.cache.IdLabelCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 18/10/2017.
 */

public class ServicePage extends BaseModel<ServicePage> {

    private String title;
    private String content;
    private String image;

    @Override
    public BaseCache getInstanceOfCache() {
        return null;
    }

    @Override
    protected Class getType() {
        return ServicePage.class;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
