package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Category;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class CategoryParser extends GeneralParser<Category> {

    public CategoryParser() {
        super();
    }

    @Override
    public Category parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Category category = null;

        category = getObjectFromCache(jo,"id");
        if(category == null){
            category = new Category();
            category.setId(safeParseLong(jo, "id"));
        }

        category.setName(safeParseString(jo, "name"));
        category.setColor(safeParseString(jo, "color"));

        return category;
    }

    @Override
    protected Class getType() {
        return Category.class;
    }
}

