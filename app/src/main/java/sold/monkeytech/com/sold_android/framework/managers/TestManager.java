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

    public String getShortPropertyString(){
        return
                "{\n" +
                        "  \"id\": 0,\n" +
                        "  \"crm_id\": \"string\",\n" +
                        "  \"cover_photo\": \"string\",\n" +
                        "  \"price\": {\n" +
                        "    \"formatted\": \"string\",\n" +
                        "    \"value\": 0\n" +
                        "  },\n" +
                        "  \"meter_price\": {\n" +
                        "    \"formatted\": \"string\",\n" +
                        "    \"value\": 0\n" +
                        "  },\n" +
                        "  \"street\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\",\n" +
                        "    \"city\": {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"house_number\": \"string\",\n" +
                        "  \"apt\": \"string\",\n" +
                        "  \"floor\": 0,\n" +
                        "  \"property_type\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\",\n" +
                        "    \"icon\": \"string\"\n" +
                        "  },\n" +
                        "  \"rooms_count\": 0,\n" +
                        "  \"bathrooms_count\": 0,\n" +
                        "  \"wcs_count\": 0,\n" +
                        "  \"floor_area\": 0,\n" +
                        "  \"plot_area\": 0,\n" +
                        "  \"property_status\": \"string\"\n" +
                        "}";
    }

    public String getshortPropList(){
        return
                "[\n" +
                        "  {\n" +
                        "    \"id\": 5,\n" +
                        "    \"crm_id\": \"SO657645780\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423493295_tgwulrvjm3_%D7%94%D7%9E%D7%92%D7%93%D7%9C%D7%99%D7%9D-%D7%91%D7%99%D7%A9%D7%95%D7%A8%D7%95%D7%9F-%D7%A7%D7%A8%D7%93%D7%99%D7%98-%D7%98%D7%95%D7%98%D7%9D-3720-x-1860.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪3,950,000.00\",\n" +
                        "      \"value\": 3950000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪28,214.00\",\n" +
                        "      \"value\": 28214\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 37615,\n" +
                        "      \"name\": \"כצנלסון\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 854,\n" +
                        "        \"name\": \"גבעתיים\",\n" +
                        "        \"created_at\": \"2018-01-28T18:03:29.113+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T18:03:29.122+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"10\",\n" +
                        "    \"apt\": \"10\",\n" +
                        "    \"floor\": 5,\n" +
                        "    \"lat\": \"32.075116\",\n" +
                        "    \"lng\": \"34.802671\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 7,\n" +
                        "      \"name\": \"פנטהאוז\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/7/penthouse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=47877300f7d66afb4d4e80d1263bc97ec72411aa6b9593b81fe935611ff06c31\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:25.429+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"4.5\",\n" +
                        "    \"bathrooms_count\": 2,\n" +
                        "    \"wcs_count\": 2,\n" +
                        "    \"floor_area\": 140,\n" +
                        "    \"plot_area\": 140,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-03-07T13:51:47.110+02:00\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"crm_id\": \"SO869712781\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1517842764043_40upfpz3ho6_terrah-holly-16329.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪4,500,000.00\",\n" +
                        "      \"value\": 4500000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪45,000.00\",\n" +
                        "      \"value\": 45000\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 34257,\n" +
                        "      \"name\": \"נחלת בנימין\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 843,\n" +
                        "        \"name\": \"תל אביב - יפו\",\n" +
                        "        \"created_at\": \"2018-01-28T18:00:22.078+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T18:01:19.560+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"85\",\n" +
                        "    \"apt\": \"3\",\n" +
                        "    \"floor\": 3,\n" +
                        "    \"lat\": \"32.06108\",\n" +
                        "    \"lng\": \"34.77264\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"דירה\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=1062cd7f9259fc0f29918a076de3216a71f649effa25024f3b8967c8b79c4ab3\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"4.0\",\n" +
                        "    \"bathrooms_count\": 2,\n" +
                        "    \"wcs_count\": 3,\n" +
                        "    \"floor_area\": 100,\n" +
                        "    \"plot_area\": 100,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-02-05T17:09:47.047+02:00\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"crm_id\": \"SO570975400\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1518611313113_cvwzj6lis8v_2017-04-23-PHOTO-00000455.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪2,500,000.00\",\n" +
                        "      \"value\": 2500000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪50,000.00\",\n" +
                        "      \"value\": 50000\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 34970,\n" +
                        "      \"name\": \"הרצל\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 843,\n" +
                        "        \"name\": \"תל אביב - יפו\",\n" +
                        "        \"created_at\": \"2018-01-28T18:00:22.078+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T18:01:46.459+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"106\",\n" +
                        "    \"apt\": \"12\",\n" +
                        "    \"floor\": 3,\n" +
                        "    \"lat\": \"32.05555\",\n" +
                        "    \"lng\": \"34.77076\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"דירה\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=1062cd7f9259fc0f29918a076de3216a71f649effa25024f3b8967c8b79c4ab3\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"1.0\",\n" +
                        "    \"bathrooms_count\": 1,\n" +
                        "    \"wcs_count\": 1,\n" +
                        "    \"floor_area\": 45,\n" +
                        "    \"plot_area\": 55,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-02-14T14:28:39.756+02:00\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"crm_id\": \"SO381879830\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520365940762_oy083lb74oc1_416668_1.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪3,525,000.00\",\n" +
                        "      \"value\": 3525000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪22,742.00\",\n" +
                        "      \"value\": 22742\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 38000,\n" +
                        "      \"name\": \"אש שלום\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 856,\n" +
                        "        \"name\": \"בת ים\",\n" +
                        "        \"created_at\": \"2018-01-28T18:03:46.856+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T18:03:46.899+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"10\",\n" +
                        "    \"apt\": \"4\",\n" +
                        "    \"floor\": 2,\n" +
                        "    \"lat\": \"32.020314\",\n" +
                        "    \"lng\": \"34.759714\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"דירה\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=1062cd7f9259fc0f29918a076de3216a71f649effa25024f3b8967c8b79c4ab3\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"5.0\",\n" +
                        "    \"bathrooms_count\": 2,\n" +
                        "    \"wcs_count\": 2,\n" +
                        "    \"floor_area\": 155,\n" +
                        "    \"plot_area\": 155,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-03-06T21:52:22.379+02:00\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 12,\n" +
                        "    \"crm_id\": \"SO367481001\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520450351464_1wdz4aumiln_COVER.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪2,450,000.00\",\n" +
                        "      \"value\": 2450000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪20,417.00\",\n" +
                        "      \"value\": 20417\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 29433,\n" +
                        "      \"name\": \"אודם\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 801,\n" +
                        "        \"name\": \"ראשון לציון\",\n" +
                        "        \"created_at\": \"2018-01-28T17:58:40.002+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T17:58:40.030+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"1\",\n" +
                        "    \"apt\": \"18\",\n" +
                        "    \"floor\": 6,\n" +
                        "    \"lat\": \"31.983969\",\n" +
                        "    \"lng\": \"34.763104\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"דירה\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=1062cd7f9259fc0f29918a076de3216a71f649effa25024f3b8967c8b79c4ab3\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"4.5\",\n" +
                        "    \"bathrooms_count\": 2,\n" +
                        "    \"wcs_count\": 2,\n" +
                        "    \"floor_area\": 120,\n" +
                        "    \"plot_area\": 120,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-03-07T21:19:14.103+02:00\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 14,\n" +
                        "    \"crm_id\": \"SO598828861\",\n" +
                        "    \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520451819603_9z3ljzmy7ko_031.jpg\",\n" +
                        "    \"price\": {\n" +
                        "      \"formatted\": \"₪5,100,000.00\",\n" +
                        "      \"value\": 5100000\n" +
                        "    },\n" +
                        "    \"meter_price\": {\n" +
                        "      \"formatted\": \"₪27,568.00\",\n" +
                        "      \"value\": 27568\n" +
                        "    },\n" +
                        "    \"street\": {\n" +
                        "      \"id\": 25973,\n" +
                        "      \"name\": \"המכבים\",\n" +
                        "      \"city\": {\n" +
                        "        \"id\": 744,\n" +
                        "        \"name\": \"הוד השרון\",\n" +
                        "        \"created_at\": \"2018-01-28T17:56:39.737+02:00\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2018-01-28T17:56:39.781+02:00\"\n" +
                        "    },\n" +
                        "    \"house_number\": \"4\",\n" +
                        "    \"apt\": \"8\",\n" +
                        "    \"floor\": 4,\n" +
                        "    \"lat\": \"32.138115\",\n" +
                        "    \"lng\": \"34.897204\",\n" +
                        "    \"property_type\": {\n" +
                        "      \"id\": 6,\n" +
                        "      \"name\": \"מיני פנטהאוז\",\n" +
                        "      \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/6/mini-penthouse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082045Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=88cacb670324545530f8c332c11621a5737f6710ee59b51c81f89094a37f2966\",\n" +
                        "      \"created_at\": \"2018-01-28T17:39:25.398+02:00\"\n" +
                        "    },\n" +
                        "    \"rooms_count\": \"5.5\",\n" +
                        "    \"bathrooms_count\": 3,\n" +
                        "    \"wcs_count\": 3,\n" +
                        "    \"floor_area\": 160,\n" +
                        "    \"plot_area\": 210,\n" +
                        "    \"property_status\": \"active\",\n" +
                        "    \"created_at\": \"2018-03-07T21:43:41.897+02:00\"\n" +
                        "  }\n" +
                        "]";
    }

    public String getLongProperty(){
        return
                "{\n" +
                        "  \"id\": 5,\n" +
                        "  \"crm_id\": \"SO657645780\",\n" +
                        "  \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423493295_tgwulrvjm3_%D7%94%D7%9E%D7%92%D7%93%D7%9C%D7%99%D7%9D-%D7%91%D7%99%D7%A9%D7%95%D7%A8%D7%95%D7%9F-%D7%A7%D7%A8%D7%93%D7%99%D7%98-%D7%98%D7%95%D7%98%D7%9D-3720-x-1860.jpg\",\n" +
                        "  \"price\": {\n" +
                        "    \"formatted\": \"₪3,950,000.00\",\n" +
                        "    \"value\": 3950000\n" +
                        "  },\n" +
                        "  \"meter_price\": {\n" +
                        "    \"formatted\": \"₪28,214.00\",\n" +
                        "    \"value\": 28214\n" +
                        "  },\n" +
                        "  \"street\": {\n" +
                        "    \"id\": 37615,\n" +
                        "    \"name\": \"כצנלסון\",\n" +
                        "    \"city\": {\n" +
                        "      \"id\": 854,\n" +
                        "      \"name\": \"גבעתיים\",\n" +
                        "      \"created_at\": \"2018-01-28T18:03:29.113+02:00\"\n" +
                        "    },\n" +
                        "    \"created_at\": \"2018-01-28T18:03:29.122+02:00\"\n" +
                        "  },\n" +
                        "  \"house_number\": \"10\",\n" +
                        "  \"apt\": \"10\",\n" +
                        "  \"floor\": 5,\n" +
                        "  \"lat\": \"32.075116\",\n" +
                        "  \"lng\": \"34.802671\",\n" +
                        "  \"property_type\": {\n" +
                        "    \"id\": 7,\n" +
                        "    \"name\": \"פנטהאוז\",\n" +
                        "    \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/7/penthouse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=3609fc393d49c6c3c5e3f6d5176680eb181c9ece88e27ee88de7579c7a15f736\",\n" +
                        "    \"created_at\": \"2018-01-28T17:39:25.429+02:00\"\n" +
                        "  },\n" +
                        "  \"rooms_count\": \"4.5\",\n" +
                        "  \"bathrooms_count\": 2,\n" +
                        "  \"wcs_count\": 2,\n" +
                        "  \"floor_area\": 140,\n" +
                        "  \"plot_area\": 140,\n" +
                        "  \"property_status\": \"active\",\n" +
                        "  \"created_at\": \"2018-03-07T13:51:47.110+02:00\",\n" +
                        "  \"description\": \"דירת גג עורפית מיוחדת ומעוצבת ע\\\"י אדריכל נוף פתוח, גג גדול עם יחידת הורים מפוארת, פרגולה מטבחון וחדר כביסה. בניין משופץ ומים חמים בחימום מרכזי. חניה פרטית בטאבו צמוד ליציאה מהעיר בלי פקקים. מציאה!\",\n" +
                        "  \"album\": [\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423493295_tgwulrvjm3_%D7%94%D7%9E%D7%92%D7%93%D7%9C%D7%99%D7%9D-%D7%91%D7%99%D7%A9%D7%95%D7%A8%D7%95%D7%9F-%D7%A7%D7%A8%D7%93%D7%99%D7%98-%D7%98%D7%95%D7%98%D7%9D-3720-x-1860.jpg\",\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423781000_rhds0e4jozs_4429aaed341fe1a9163d3e086fbd7f42.jpg\",\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423781037_8d99mp06osl_%D7%97%D7%93%D7%A8+%D7%90%D7%9E%D7%91%D7%98%D7%99%D7%94.JPG\",\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423781024_all0y3uwmiw_penthouse-c01_00000-1200x510.jpg\",\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423781012_6yodg2kvx5p_Hi-Tower-%D7%9E%D7%98%D7%91%D7%97.jpg\",\n" +
                        "    \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520423781042_hlzvfmzfm0o_%D7%A4%D7%A0%D7%983-3-maman-868x500.jpeg\"\n" +
                        "  ],\n" +
                        "  \"virtual_tour_link\": \"\",\n" +
                        "  \"apartments_count\": 10,\n" +
                        "  \"apartments_per_floor\": 2,\n" +
                        "  \"floors_count\": 5,\n" +
                        "  \"parking_slots\": 1,\n" +
                        "  \"leads\": \"translation missing: he.activerecord.attributes.property.lead_N, translation missing: he.activerecord.attributes.property.lead_E\",\n" +
                        "  \"direction\": \"rear\",\n" +
                        "  \"built_at\": 2010,\n" +
                        "  \"is_foreclosure\": false,\n" +
                        "  \"property_tax\": {\n" +
                        "    \"formatted\": \"₪600.00\",\n" +
                        "    \"value\": 600\n" +
                        "  },\n" +
                        "  \"house_committee_tax\": {\n" +
                        "    \"formatted\": \"₪800.00\",\n" +
                        "    \"value\": 800\n" +
                        "  },\n" +
                        "  \"house_committee_tax_details\": null,\n" +
                        "  \"open_house\": [\n" +
                        "    {\n" +
                        "      \"id\": 1519855200,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 1,\n" +
                        "      \"month\": \"מרץ\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1519905600,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1519907400,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1520460000,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 8,\n" +
                        "      \"month\": \"מרץ\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1520510400,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1520512200,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1521064800,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 15,\n" +
                        "      \"month\": \"מרץ\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1521115200,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1521117000,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1521669600,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 22,\n" +
                        "      \"month\": \"מרץ\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1521720000,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1521721800,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1522270800,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 29,\n" +
                        "      \"month\": \"מרץ\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1522321200,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1522323000,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1522875600,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 5,\n" +
                        "      \"month\": \"אפר\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1522926000,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1522927800,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1523480400,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 12,\n" +
                        "      \"month\": \"אפר\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1523530800,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1523532600,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1524085200,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 19,\n" +
                        "      \"month\": \"אפר\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1524135600,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1524137400,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1524690000,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 26,\n" +
                        "      \"month\": \"אפר\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1524740400,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1524742200,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1525294800,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 3,\n" +
                        "      \"month\": \"מאי\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1525345200,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1525347000,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1525899600,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 10,\n" +
                        "      \"month\": \"מאי\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1525950000,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1525951800,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1526504400,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 17,\n" +
                        "      \"month\": \"מאי\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1526554800,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1526556600,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1527109200,\n" +
                        "      \"wday\": \"חמישי\",\n" +
                        "      \"day\": 24,\n" +
                        "      \"month\": \"מאי\",\n" +
                        "      \"slots\": [\n" +
                        "        {\n" +
                        "          \"id\": 1527159600,\n" +
                        "          \"name\": \"02:00 PM\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 1527161400,\n" +
                        "          \"name\": \"02:30 PM\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"property_features\": [\n" +
                        "    {\n" +
                        "      \"id\": 6,\n" +
                        "      \"feature\": {\n" +
                        "        \"id\": 18,\n" +
                        "        \"name\": \"מרפסת\",\n" +
                        "        \"created_at\": \"2018-01-28T17:41:12.158+02:00\"\n" +
                        "      },\n" +
                        "      \"meta\": [\n" +
                        "        {\n" +
                        "          \"key\": \"טירוף\",\n" +
                        "          \"value\": \"\"\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"images\": [\n" +
                        "        \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/property_features/1520424656223_d3cy8w1b5p_%D7%94%D7%9E%D7%92%D7%93%D7%9C%D7%99%D7%9D-%D7%91%D7%99%D7%A9%D7%95%D7%A8%D7%95%D7%9F-%D7%A7%D7%A8%D7%93%D7%99%D7%98-%D7%98%D7%95%D7%98%D7%9D-3720-x-1860.jpg\"\n" +
                        "      ],\n" +
                        "      \"created_at\": \"2018-03-07T14:10:59.912+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 7,\n" +
                        "      \"feature\": {\n" +
                        "        \"id\": 3,\n" +
                        "        \"name\": \"מטבח ופינת אוכל\",\n" +
                        "        \"created_at\": \"2018-01-28T17:41:12.093+02:00\"\n" +
                        "      },\n" +
                        "      \"meta\": [\n" +
                        "        {\n" +
                        "          \"key\": \"אי\",\n" +
                        "          \"value\": null\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"images\": [\n" +
                        "        \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/property_features/1520424692162_vdtibbv7ije_Hi-Tower-%D7%9E%D7%98%D7%91%D7%97.jpg\"\n" +
                        "      ],\n" +
                        "      \"created_at\": \"2018-03-07T14:11:32.681+02:00\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"tax_records\": [],\n" +
                        "  \"pois\": [\n" +
                        "    {\n" +
                        "      \"id\": 2,\n" +
                        "      \"name\": \"הירו ראמן\",\n" +
                        "      \"description\": null,\n" +
                        "      \"category\": {\n" +
                        "        \"id\": 4,\n" +
                        "        \"name\": \"מסעדות\",\n" +
                        "        \"color\": \"#f89346\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:55.972+02:00\"\n" +
                        "      },\n" +
                        "      \"address\": \"לבונטין 14, תל אביב יפו, ישראל\",\n" +
                        "      \"lat\": \"32.061841\",\n" +
                        "      \"lng\": \"34.775689\",\n" +
                        "      \"rating\": 0,\n" +
                        "      \"meta\": [],\n" +
                        "      \"created_at\": \"2018-02-13T14:52:07.435+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 5,\n" +
                        "      \"name\": \"תחנה מרכזית תל אביב\",\n" +
                        "      \"description\": null,\n" +
                        "      \"category\": {\n" +
                        "        \"id\": 3,\n" +
                        "        \"name\": \"תחבורה ציבורית\",\n" +
                        "        \"color\": \"#04aeef\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:55.967+02:00\"\n" +
                        "      },\n" +
                        "      \"address\": \"לוינסקי 108, תל אביב יפו, ישראל\",\n" +
                        "      \"lat\": \"32.056655\",\n" +
                        "      \"lng\": \"34.779519\",\n" +
                        "      \"rating\": 0,\n" +
                        "      \"meta\": [],\n" +
                        "      \"created_at\": \"2018-02-13T15:00:31.051+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 4,\n" +
                        "      \"name\": \"באן\",\n" +
                        "      \"description\": null,\n" +
                        "      \"category\": {\n" +
                        "        \"id\": 4,\n" +
                        "        \"name\": \"מסעדות\",\n" +
                        "        \"color\": \"#f89346\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:55.972+02:00\"\n" +
                        "      },\n" +
                        "      \"address\": \"הרצל 19, תל אביב יפו, ישראל\",\n" +
                        "      \"lat\": \"32.061443\",\n" +
                        "      \"lng\": \"34.770447\",\n" +
                        "      \"rating\": 0,\n" +
                        "      \"meta\": [],\n" +
                        "      \"created_at\": \"2018-02-13T14:56:55.528+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 3,\n" +
                        "      \"name\": \"הסלוף\",\n" +
                        "      \"description\": null,\n" +
                        "      \"category\": {\n" +
                        "        \"id\": 4,\n" +
                        "        \"name\": \"מסעדות\",\n" +
                        "        \"color\": \"#f89346\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:55.972+02:00\"\n" +
                        "      },\n" +
                        "      \"address\": \"נחלת בנימין 119, תל אביב יפו, ישראל\",\n" +
                        "      \"lat\": \"32.058478\",\n" +
                        "      \"lng\": \"34.772168\",\n" +
                        "      \"rating\": 0,\n" +
                        "      \"meta\": [],\n" +
                        "      \"created_at\": \"2018-02-13T14:54:07.558+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"רוגוזין\",\n" +
                        "      \"description\": \"בית הספר של ילדי העובדים הזרים\",\n" +
                        "      \"category\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"בתי ספר\",\n" +
                        "        \"color\": \"#c20e1a\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:55.955+02:00\"\n" +
                        "      },\n" +
                        "      \"address\": \"העלייה 50, תל אביב יפו, ישראל\",\n" +
                        "      \"lat\": \"32.056857\",\n" +
                        "      \"lng\": \"34.772857\",\n" +
                        "      \"rating\": 0,\n" +
                        "      \"meta\": [],\n" +
                        "      \"created_at\": \"2018-02-12T19:18:35.231+02:00\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"nearby_properties\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"crm_id\": \"SO869712781\",\n" +
                        "      \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1517842764043_40upfpz3ho6_terrah-holly-16329.jpg\",\n" +
                        "      \"price\": {\n" +
                        "        \"formatted\": \"₪4,500,000.00\",\n" +
                        "        \"value\": 4500000\n" +
                        "      },\n" +
                        "      \"meter_price\": {\n" +
                        "        \"formatted\": \"₪45,000.00\",\n" +
                        "        \"value\": 45000\n" +
                        "      },\n" +
                        "      \"street\": {\n" +
                        "        \"id\": 34257,\n" +
                        "        \"name\": \"נחלת בנימין\",\n" +
                        "        \"city\": {\n" +
                        "          \"id\": 843,\n" +
                        "          \"name\": \"תל אביב - יפו\",\n" +
                        "          \"created_at\": \"2018-01-28T18:00:22.078+02:00\"\n" +
                        "        },\n" +
                        "        \"created_at\": \"2018-01-28T18:01:19.560+02:00\"\n" +
                        "      },\n" +
                        "      \"house_number\": \"85\",\n" +
                        "      \"apt\": \"3\",\n" +
                        "      \"floor\": 3,\n" +
                        "      \"lat\": \"32.06108\",\n" +
                        "      \"lng\": \"34.77264\",\n" +
                        "      \"property_type\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"דירה\",\n" +
                        "        \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=44adcba7754779c35e295aafe6be4b197d46f085e48ea7cbe9c034ba93b6d031\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "      },\n" +
                        "      \"rooms_count\": \"4.0\",\n" +
                        "      \"bathrooms_count\": 2,\n" +
                        "      \"wcs_count\": 3,\n" +
                        "      \"floor_area\": 100,\n" +
                        "      \"plot_area\": 100,\n" +
                        "      \"property_status\": \"active\",\n" +
                        "      \"created_at\": \"2018-02-05T17:09:47.047+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 2,\n" +
                        "      \"crm_id\": \"SO570975400\",\n" +
                        "      \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1518611313113_cvwzj6lis8v_2017-04-23-PHOTO-00000455.jpg\",\n" +
                        "      \"price\": {\n" +
                        "        \"formatted\": \"₪2,500,000.00\",\n" +
                        "        \"value\": 2500000\n" +
                        "      },\n" +
                        "      \"meter_price\": {\n" +
                        "        \"formatted\": \"₪50,000.00\",\n" +
                        "        \"value\": 50000\n" +
                        "      },\n" +
                        "      \"street\": {\n" +
                        "        \"id\": 34970,\n" +
                        "        \"name\": \"הרצל\",\n" +
                        "        \"city\": {\n" +
                        "          \"id\": 843,\n" +
                        "          \"name\": \"תל אביב - יפו\",\n" +
                        "          \"created_at\": \"2018-01-28T18:00:22.078+02:00\"\n" +
                        "        },\n" +
                        "        \"created_at\": \"2018-01-28T18:01:46.459+02:00\"\n" +
                        "      },\n" +
                        "      \"house_number\": \"106\",\n" +
                        "      \"apt\": \"12\",\n" +
                        "      \"floor\": 3,\n" +
                        "      \"lat\": \"32.05555\",\n" +
                        "      \"lng\": \"34.77076\",\n" +
                        "      \"property_type\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"דירה\",\n" +
                        "        \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=44adcba7754779c35e295aafe6be4b197d46f085e48ea7cbe9c034ba93b6d031\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "      },\n" +
                        "      \"rooms_count\": \"1.0\",\n" +
                        "      \"bathrooms_count\": 1,\n" +
                        "      \"wcs_count\": 1,\n" +
                        "      \"floor_area\": 45,\n" +
                        "      \"plot_area\": 55,\n" +
                        "      \"property_status\": \"active\",\n" +
                        "      \"created_at\": \"2018-02-14T14:28:39.756+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 3,\n" +
                        "      \"crm_id\": \"SO381879830\",\n" +
                        "      \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520365940762_oy083lb74oc1_416668_1.jpg\",\n" +
                        "      \"price\": {\n" +
                        "        \"formatted\": \"₪3,525,000.00\",\n" +
                        "        \"value\": 3525000\n" +
                        "      },\n" +
                        "      \"meter_price\": {\n" +
                        "        \"formatted\": \"₪22,742.00\",\n" +
                        "        \"value\": 22742\n" +
                        "      },\n" +
                        "      \"street\": {\n" +
                        "        \"id\": 38000,\n" +
                        "        \"name\": \"אש שלום\",\n" +
                        "        \"city\": {\n" +
                        "          \"id\": 856,\n" +
                        "          \"name\": \"בת ים\",\n" +
                        "          \"created_at\": \"2018-01-28T18:03:46.856+02:00\"\n" +
                        "        },\n" +
                        "        \"created_at\": \"2018-01-28T18:03:46.899+02:00\"\n" +
                        "      },\n" +
                        "      \"house_number\": \"10\",\n" +
                        "      \"apt\": \"4\",\n" +
                        "      \"floor\": 2,\n" +
                        "      \"lat\": \"32.020314\",\n" +
                        "      \"lng\": \"34.759714\",\n" +
                        "      \"property_type\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"דירה\",\n" +
                        "        \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=44adcba7754779c35e295aafe6be4b197d46f085e48ea7cbe9c034ba93b6d031\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "      },\n" +
                        "      \"rooms_count\": \"5.0\",\n" +
                        "      \"bathrooms_count\": 2,\n" +
                        "      \"wcs_count\": 2,\n" +
                        "      \"floor_area\": 155,\n" +
                        "      \"plot_area\": 155,\n" +
                        "      \"property_status\": \"active\",\n" +
                        "      \"created_at\": \"2018-03-06T21:52:22.379+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 12,\n" +
                        "      \"crm_id\": \"SO367481001\",\n" +
                        "      \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520450351464_1wdz4aumiln_COVER.jpg\",\n" +
                        "      \"price\": {\n" +
                        "        \"formatted\": \"₪2,450,000.00\",\n" +
                        "        \"value\": 2450000\n" +
                        "      },\n" +
                        "      \"meter_price\": {\n" +
                        "        \"formatted\": \"₪20,417.00\",\n" +
                        "        \"value\": 20417\n" +
                        "      },\n" +
                        "      \"street\": {\n" +
                        "        \"id\": 29433,\n" +
                        "        \"name\": \"אודם\",\n" +
                        "        \"city\": {\n" +
                        "          \"id\": 801,\n" +
                        "          \"name\": \"ראשון לציון\",\n" +
                        "          \"created_at\": \"2018-01-28T17:58:40.002+02:00\"\n" +
                        "        },\n" +
                        "        \"created_at\": \"2018-01-28T17:58:40.030+02:00\"\n" +
                        "      },\n" +
                        "      \"house_number\": \"1\",\n" +
                        "      \"apt\": \"18\",\n" +
                        "      \"floor\": 6,\n" +
                        "      \"lat\": \"31.983969\",\n" +
                        "      \"lng\": \"34.763104\",\n" +
                        "      \"property_type\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"דירה\",\n" +
                        "        \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/1/apartment.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=44adcba7754779c35e295aafe6be4b197d46f085e48ea7cbe9c034ba93b6d031\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:24.994+02:00\"\n" +
                        "      },\n" +
                        "      \"rooms_count\": \"4.5\",\n" +
                        "      \"bathrooms_count\": 2,\n" +
                        "      \"wcs_count\": 2,\n" +
                        "      \"floor_area\": 120,\n" +
                        "      \"plot_area\": 120,\n" +
                        "      \"property_status\": \"active\",\n" +
                        "      \"created_at\": \"2018-03-07T21:19:14.103+02:00\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 14,\n" +
                        "      \"crm_id\": \"SO598828861\",\n" +
                        "      \"cover_photo\": \"https://s3-eu-west-1.amazonaws.com/sold-staging/uploads/properties/1520451819603_9z3ljzmy7ko_031.jpg\",\n" +
                        "      \"price\": {\n" +
                        "        \"formatted\": \"₪5,100,000.00\",\n" +
                        "        \"value\": 5100000\n" +
                        "      },\n" +
                        "      \"meter_price\": {\n" +
                        "        \"formatted\": \"₪27,568.00\",\n" +
                        "        \"value\": 27568\n" +
                        "      },\n" +
                        "      \"street\": {\n" +
                        "        \"id\": 25973,\n" +
                        "        \"name\": \"המכבים\",\n" +
                        "        \"city\": {\n" +
                        "          \"id\": 744,\n" +
                        "          \"name\": \"הוד השרון\",\n" +
                        "          \"created_at\": \"2018-01-28T17:56:39.737+02:00\"\n" +
                        "        },\n" +
                        "        \"created_at\": \"2018-01-28T17:56:39.781+02:00\"\n" +
                        "      },\n" +
                        "      \"house_number\": \"4\",\n" +
                        "      \"apt\": \"8\",\n" +
                        "      \"floor\": 4,\n" +
                        "      \"lat\": \"32.138115\",\n" +
                        "      \"lng\": \"34.897204\",\n" +
                        "      \"property_type\": {\n" +
                        "        \"id\": 6,\n" +
                        "        \"name\": \"מיני פנטהאוז\",\n" +
                        "        \"icon\": \"https://sold-staging.s3.eu-west-1.amazonaws.com/uploads/property_type/icon/6/mini-penthouse.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIVAXMPBCDPQPRSQQ%2F20180308%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20180308T082214Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=28ffcd5ce3db42cb4969663ad6c78f8d638cca6be9f9504d55eeab1bf34015d8\",\n" +
                        "        \"created_at\": \"2018-01-28T17:39:25.398+02:00\"\n" +
                        "      },\n" +
                        "      \"rooms_count\": \"5.5\",\n" +
                        "      \"bathrooms_count\": 3,\n" +
                        "      \"wcs_count\": 3,\n" +
                        "      \"floor_area\": 160,\n" +
                        "      \"plot_area\": 210,\n" +
                        "      \"property_status\": \"active\",\n" +
                        "      \"created_at\": \"2018-03-07T21:43:41.897+02:00\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
    }


}
