//package sold.monkeytech.com.sold_android.framework.parsers;
//
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by MonkeyFather on 28/08/2017.
// */
//
//public class IdLabelParser extends GeneralParser<IdLabel> {
//
//    String labelParseString = "Label";
//
//    public IdLabelParser() {
//        super();
//    }
//
//    public IdLabelParser(String labelParseString) {
//        super();
//        if(!TextUtils.isEmpty(labelParseString))
//            this.labelParseString = labelParseString;
//    }
//
//    @Override
//    public IdLabel parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {
//
//        IdLabel idLabel = new IdLabel();
//
//        idLabel.setId(safeParseLong(jo, "Id"));
//        idLabel.setLabel(safeParseString(jo, labelParseString));
//
//        return idLabel;
//    }
//
//    @Override
//    protected Class getType() {
//        return IdLabel.class;
//    }
//}
//
