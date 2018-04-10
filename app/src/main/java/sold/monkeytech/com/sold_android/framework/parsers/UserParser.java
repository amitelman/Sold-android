package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;


/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class UserParser extends GeneralParser<User> {

    public UserParser() {
        super();
    }

    @Override
    public User parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        User user = null;

        user = getObjectFromCache(jo,"id");
        if(user == null){
            user = new User();
            user.setId(safeParseLong(jo, "id"));
        }

        user.setPhoneNumber(safeParseString(jo, "phone_number"));
        user.setFirstName(safeParseString(jo, "first_name"));
        user.setLastName(safeParseString(jo, "last_name"));
        user.setEmail(safeParseString(jo, "email"));
        user.setPropertiesCount(safeParseInt(jo, "properties_count"));
        user.setFavoritesCount(safeParseInt(jo, "favorites_count"));
        user.setSearchCount(safeParseInt(jo, "searches_count"));

        return user;
    }

    @Override
    protected Class getType() {
        return User.class;
    }
}


