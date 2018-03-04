package sold.monkeytech.com.sold_android.framework.parsers.abs;


import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by eli on 13/03/14.
 */
public abstract class GeneralParser<T extends BaseModel> {
    protected T getObjectFromCache(JSONObject jo) throws JSONException {
        return getObjectFromCache(jo, null, null);
    }

    protected T getObjectFromCache(JSONObject jo, Long outerId) throws JSONException {
        return getObjectFromCache(jo, outerId, null);
    }

    protected T getObjectFromCache(JSONObject jo, String strId) throws JSONException {
        return getObjectFromCache(jo, null, strId);
    }

    protected T getObjectFromCache(JSONObject jo, Long outerId, String strId) throws JSONException {
        try {
            final Class<T> entityType = getType();
            BaseModel newModel = entityType.newInstance();
            Long id = outerId == null ? safeParseLong(jo, strId == null ? "Id" : strId) : outerId;
            BaseModel obj = newModel.getInstanceOfCache().getObjectById(id);
            if (obj == null) {
                obj = newModel;
                obj.setId(id);
                obj.getInstanceOfCache().addObject(obj);
            }
            return (T) obj;
        } catch (Exception e) {
            Log.d("wow","whattt? " + e);
            return null;
        }
    }


//    protected T getObjectFromCache(JSONObject jo, Long outerId) throws JSONException {
//        try {
//            final Class<T> entityType = getType();
//            BaseModel newModel = entityType.newInstance();
//            Long id = outerId == null ? safeParseLong(jo, "$id") : outerId;
//            BaseModel obj = newModel.getInstanceOfCache().getObjectById(id);
//            if (obj == null) {
//                obj = newModel;
//                obj.setId(id);
//                obj.getInstanceOfCache().addObject(obj);
//            }
//            return (T) obj;
//        } catch (Exception e) {
//            Log.d("wow","whattt? " + e);
//            return null;
//        }
//    }

    protected long getHash(String str) {
        int hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * 31 + str.charAt(i);
        }
        return hash;
    }


    public List<T> parseToList(String s) {
        List<T> list = new ArrayList<T>();
        try {
            return parseToList(new JSONArray(s));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<T> parseToList(JSONArray ja) {
        List<T> list = new ArrayList<T>();
        try {
            JSONArray jsonBoardList = ja;
            for (int i = 0; i < jsonBoardList.length(); i++) {
                JSONObject jsonObject = jsonBoardList.getJSONObject(i);
                T user = parseToSingle(jsonObject);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T parseToSingle(String s) {
        JSONObject jsonBoard = null;
        try {
            jsonBoard = new JSONObject(s);
            T user = parseToSingle(jsonBoard);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract T parseToSingle(JSONObject json) throws JSONException, InstantiationException, IllegalAccessException;

    public Long safeParseLong(JSONObject jo, String name) throws JSONException {
        try {
            return jo.getLong(name);
        } catch (Exception e) {
            return 0l;
        }
    }

    public String safeParseString(JSONObject jo, String name) throws JSONException {
        try {
            String s = jo.getString(name);
            if (s.equals("null"))
                throw new Exception();
            return s;
        } catch (Exception e) {
            return "";
        }
    }

    public Integer safeParseInt(JSONObject jo, String name) throws JSONException {
        try {
            return jo.getInt(name);
        } catch (Exception e) {
            return 0;
        }
    }

    public Double safeParseDouble(JSONObject jo, String name) throws JSONException {
        try {
            return jo.getDouble(name);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public Boolean safeParseBoolean(JSONObject jo, String name) throws JSONException {
        try {
            return jo.getBoolean(name);
        } catch (Exception e) {
            return false;
        }
    }

    protected abstract Class getType();

}