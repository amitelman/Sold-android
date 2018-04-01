//package sold.monkeytech.com.sold_android.ui.adapters;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.text.Spannable;
//import android.text.SpannableStringBuilder;
//import android.text.style.StyleSpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.List;
//
//import sold.monkeytech.com.sold_android.R;
//import sold.monkeytech.com.sold_android.framework.models.IdLabel;
//import sold.monkeytech.com.sold_android.framework.models.Meta;
//import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
//
///**
// * Created by monkey on 25/06/2015.
// */
//public class PropertyFeaturesAdapter extends BaseAdapter implements View.OnClickListener {
//    private Context context;
//    private List<PropertyFeatures> features;
//    private LayoutInflater inflater;
//    private PropertyFeaturesImagesAdapter adapter;
//
//    public PropertyFeaturesAdapter(Context context, List<PropertyFeatures> features) {
//        this.context = context;
//        if (features != null)
//            this.features = features;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        if (features != null)
//            return features.size();
//        return 0;
//    }
//
//    @Override
//    public PropertyFeatures getItem(int i) {
//        return features.get(i);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//
//    public static class BaseViewHolder {
//        LinearLayout bkg;
//        TextView title;
//        TextView desc;
//        RecyclerView imagesView;
//
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        BaseViewHolder baseViewHolder = new BaseViewHolder();
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.property_feature_item, parent, false);
//            baseViewHolder.bkg = convertView.findViewById(R.id.featureItemLayout);
//            baseViewHolder.title = convertView.findViewById(R.id.featureItemTitle);
//            baseViewHolder.desc = convertView.findViewById(R.id.featureItemValue);
//            baseViewHolder.imagesView = convertView.findViewById(R.id.featureItemList);
//
//            convertView.setTag(baseViewHolder);
//        } else {
//            baseViewHolder = (BaseViewHolder) convertView.getTag();
//        }
//
//        final PropertyFeatures item = getItem(position);
//        baseViewHolder.title.setText("• " + item.getFeatureName());
//
//        final SpannableStringBuilder descFinal = new SpannableStringBuilder("");
//        final SpannableStringBuilder desc = new SpannableStringBuilder("");
//        for(Meta m : item.getMeta()){
//            desc.append(m.getKey() + ": " + m.getValue() + ", ");
//
//            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//            desc.setSpan(bss, m.getKey().length() + 2, (m.getKey().length() + 2) + m.getValue().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//            descFinal.append(desc);
//            desc.clear();
//        }
//        baseViewHolder.desc.setText(descFinal.subSequence(0, descFinal.length() - 2));
//
//        adapter = new PropertyFeaturesImagesAdapter(context, item.getImages());
//        baseViewHolder.imagesView.setAdapter(adapter);
//
//        return convertView;
//    }
//
//
//}
//
//
