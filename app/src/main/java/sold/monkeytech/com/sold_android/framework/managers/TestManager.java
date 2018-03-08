package sold.monkeytech.com.sold_android.framework.managers;

import org.json.JSONArray;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class TestManager {

    public static TestManager instance;

    public static TestManager getInstance(){
        if(instance == null)
            instance = new TestManager();
        return instance;
    }

    public String getPropertyString(){
        return "{ \"id\": 5, \"crm_id\": \"SO657645780\", \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423493295_tgwulrvjm3_%D7%94%D7%9E%D7%92%D7%93%D7%9C%D7%99%D7%9D-%D7%91%D7%99%D7%A9%D7%95%D7%A8%D7%95%D7%9F-%D7%A7%D7%A8%D7%93%D7%99%D7%98-%D7%98%D7%95%D7%98%D7%9D-3720-x-1860.jpg\", \"price\": { \"formatted\": \"₪3,950,000.00\", \"value\": 3950000 }, \"meter_price\": { \"formatted\": \"₪28,214.00\", \"value\": 28214 }, \"street\": { \"id\": 37615, \"name\": \"כצנלסון\", \"city\": { \"id\": 854, \"name\": \"גבעתיים\", \"created_at\": \"2018-01-28T18:03:29.113+02:00\" }, \"created_at\": \"2018-01-28T18:03:29.122+02:00\" }, \"house_number\": \"10\", \"apt\": \"10\", \"floor\": 5, \"lat\": \"32.075116\", \"lng\": \"34.802671\", \"property_type\": { \"id\": 7, \"name\": \"פנטהאוז\", \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/7/penthouse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=47877300f7d66afb4d4e80d1263bc97ec72411aa6b9593b81fe935611ff06c31\", \"created_at\": \"2018-01-28T17:39:25.429+02:00\" }, \"rooms_count\": \"4.5\", \"bathrooms_count\": 2, \"wcs_count\": 2, \"floor_area\": 140, \"plot_area\": 140, \"property_status\": \"active\", \"created_at\": \"2018-03-07T13:51:47.110+02:00\" }, { \"id\": 1, \"crm_id\": \"SO869712781\", \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1517842764043_40upfpz3ho6_terrah-holly-16329.jpg\", \"price\": { \"formatted\": \"₪4,500,000.00\", \"value\": 4500000 }, \"meter_price\": { \"formatted\": \"₪45,000.00\", \"value\": 45000 }, \"street\": { \"id\": 34257, \"name\": \"נחלת בנימין\", \"city\": { \"id\": 843, \"name\": \"תל אביב - יפו\", \"created_at\": \"2018-01-28T18:00:22.078+02:00\" }, \"created_at\": \"2018-01-28T18:01:19.560+02:00\" }, \"house_number\": \"85\", \"apt\": \"3\", \"floor\": 3, \"lat\": \"32.06108\", \"lng\": \"34.77264\", \"property_type\": { \"id\": 1, \"name\": \"דירה\", \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=1062cd7f9259fc0f29918a076de3216a71f649effa25024f3b8967c8b79c4ab3\", \"created_at\": \"2018-01-28T17:39:24.994+02:00\" }, \"rooms_count\": \"4.0\", \"bathrooms_count\": 2, \"wcs_count\": 3, \"floor_area\": 100, \"plot_area\": 100, \"property_status\": \"active\", \"created_at\": \"2018-02-05T17:09:47.047+02:00\" }";
    }


}
