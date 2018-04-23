package sold.monkeytech.com.sold_android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityDealsHistoryBinding;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.TaxRecord;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.TaxHistoryAdapter;

public class DealsHistoryActivity extends BaseActivity {

    private static Property propertyStat;
    private Property curProperty;
    private ExpandableListView expListView;
    private TaxHistoryAdapter listAdapter;

    private HashMap<Integer, String> parent;
    private HashMap<HashMap<Integer, String>, String> children;
    private ActivityDealsHistoryBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_deals_history);

        if(propertyStat != null){
            curProperty = propertyStat;
            propertyStat = null;
        }

        initList(curProperty);

    }

    private void initList(Property property) {
        mBinding.dealsHistoryActContainer.removeAllViews();
        List<Property> nearbyDeals = property.getNearbyProperties();
        if (nearbyDeals.size() > 0) {
            mBinding.dealsHistoryActContainer.setVisibility(View.VISIBLE);
            for (int i = -1; i < nearbyDeals.size(); i++) {
                View child = getLayoutInflater().inflate(R.layout.poi_item, null);
                RelativeLayout bkg = child.findViewById(R.id.poiItemBkg);
                TextView title = child.findViewById(R.id.poiItemTitle);
                TextView distance = child.findViewById(R.id.poiItemDistance);
                if (i == -1) {
                    title.setText("כתובת");
                    title.setTextColor(getResources().getColor(R.color.white));
                    distance.setText("סכום");
                    distance.setTextColor(getResources().getColor(R.color.white));
                    bkg.setBackgroundColor(getResources().getColor(R.color.dark_grey_blue_two));
                } else {
                    if (i % 2 == 0) {
                        bkg.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        bkg.setBackgroundColor(getResources().getColor(R.color.silver_two));
                    }
                    title.setText(nearbyDeals.get(i).getFullAddress());
                    distance.setText(nearbyDeals.get(i).getPrice().getFormatted());
                }
                mBinding.dealsHistoryActContainer.addView(child);


            }
        }else{
            mBinding.dealsHistoryActContainer.setVisibility(View.GONE);
        }

        mBinding.dealsHistoryActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static void startWithProperty(Context context, Property curProperty) {
        Intent intent = new Intent(context, DealsHistoryActivity.class);
        propertyStat = curProperty;
        context.startActivity(intent);
    }
}
