package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivitySearchLocationBinding;
import sold.monkeytech.com.sold_android.framework.models.Address;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.serverapi.utils.ApiGetLocation;
import sold.monkeytech.com.sold_android.framework.serverapi.utils.ApiGetLocationAutoComplete;
import sold.monkeytech.com.sold_android.ui.adapters.SearchLocationAdapter;

public class SearchLocationActivity extends Activity {

    public static final int CHOOSE_CITY = 0;
    public static final int CHOOSE_STREET = 1;
    public static final int CHOOSE_BOTH = 2;

    private ActivitySearchLocationBinding mBinding;
    private SearchLocationAdapter adapter;
    private int type;
    private int cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_location);

        type = getIntent().getIntExtra("type", CHOOSE_CITY);
        cityId = getIntent().getIntExtra("cityId", -1);
        initUi();
    }

    private void initUi() {
        adapter = new SearchLocationAdapter(this, type, null, onLocationClickAction());
        mBinding.searchLocationActListView.setAdapter(adapter);

        mBinding.searchLocationActInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    String str = s.toString();
                    final Handler handler = new Handler();
                    if(type == CHOOSE_BOTH){
                        new ApiGetLocationAutoComplete(SearchLocationActivity.this).request(str, new TAction<List<Location>>() {
                            @Override
                            public void execute(final List<Location> locations) {
                                Log.d("wow","Locations: " + locations);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.refreshList(locations);
                                    }
                                });
                            }
                        }, new Action() {
                            @Override
                            public void execute() {

                            }
                        });
                    }else{
                        new ApiGetLocation(SearchLocationActivity.this).request(str, cityId, type, new TAction<List<Location>>() {
                            @Override
                            public void execute(final List<Location> addresses) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.refreshList(addresses);
                                    }
                                });
                            }
                        }, new Action() {
                            @Override
                            public void execute() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SearchLocationActivity.this, "לא נמצאו תוצאות נוספות", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });
    }

    private TAction<Location> onLocationClickAction() {
        return new TAction<Location>() {
            @Override
            public void execute(Location location) {
                Intent intent = new Intent();
                if(type == CHOOSE_CITY){
                    intent.putExtra("cityName", location.getLocationName());
                    intent.putExtra("cityId", location.getId());
                    if(location.getLat() != 0){
                        intent.putExtra("lat",location.getLat());
                        intent.putExtra("lng",location.getLng());
                    }
                }else if(type == CHOOSE_STREET){
                    intent.putExtra("streetName", location.getLocationName());
                    intent.putExtra("streetId", location.getId());
                    if(location.getLat() != 0){
                        intent.putExtra("lat",location.getLat());
                        intent.putExtra("lng",location.getLng());
                    }
                }else{
                    intent.putExtra("cityId", location.getId());
                    intent.putExtra("cityName", location.getLocationName());
                    intent.putExtra("type", location.getLocationType());
                    if(location.getLat() != 0){
                        intent.putExtra("lat",location.getLat());
                        intent.putExtra("lng",location.getLng());
                    }
                }
                setResult(RESULT_OK, intent);
                finish();

            }
        };
    }


}

