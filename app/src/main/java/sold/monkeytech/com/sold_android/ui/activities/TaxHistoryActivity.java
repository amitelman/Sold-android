package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.TaxRecord;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.TaxHistoryAdapter;

public class TaxHistoryActivity extends BaseActivity {

    private ExpandableListView expListView;
    private TaxHistoryAdapter listAdapter;

    private HashMap<Integer, String> parent;
    private HashMap<HashMap<Integer, String>, String> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_history);

        initUi();

    }

    private void initUi() {
        final Handler handler = new Handler();
        new ApiGetPropertyById(this).request(1, new TAction<Property>() {

            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startDaShit(property);

                    }
                });
            }
        }, null);
    }

    private void startDaShit(Property property) {

        expListView = (ExpandableListView) findViewById(R.id.taxHistoryActList);

        // preparing list data
        prepareListData(property.getTaxRecords());

        listAdapter = new TaxHistoryAdapter(this, property.getTaxRecords());

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData(List<TaxRecord> taxRecords) {
        parent = new HashMap<>();
        children = new HashMap<>();

        for(TaxRecord tr : taxRecords){
            parent.put(tr.getYear(), tr.getAmount().getFormatted());
            children.put(parent, tr.getDescription());
        }
    }
}
