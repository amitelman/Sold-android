package sold.monkeytech.com.sold_android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.imanoweb.calendarview.CustomCalendarView;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityMyPropertyPageBinding;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiDeleteOpenHouseSlot;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiGetMyPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseDaysAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseHoursAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyFeaturesImagesAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyPageHeaderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.utils.ItemOffsetDecoration;

public class MyPropertyPageActivity extends Activity {

    private static Property propertyStat;
    private Property curProperty;
    private ActivityMyPropertyPageBinding mBinding;

    private PropertyPageHeaderAdapter pagerAdapter;
    private int dotscount;
    private ImageView[] dots;
    private OpenHouseHoursAdapter hoursAdapter;
    private OpenHouseDaysAdapter daysAdapter;
    private boolean descriptionExpanded = false;
    private PropertyFeaturesImagesAdapter imagesAdapter;
    private ItemOffsetDecoration daysItemDecoration = null;
    private ItemOffsetDecoration hoursItemDecoration = null;

    List<OpenHouse> deleteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_property_page);

        if(propertyStat != null)
            getProperty(propertyStat);
    }

    private void getProperty(final Property fullProperty) {
        final Handler handler = new Handler();
        mBinding.myPropPageActPb.show();
        new ApiGetMyPropertyById(this).request(propertyStat.getId(), new TAction<Property>() {
            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        curProperty = fullProperty;
                        propertyStat = null;
                        initUi();
                    }
                });
            }
        }, new Action() {
            @Override
            public void execute() {

            }
        });
    }

    private void initUi() {

        initHeaderPager();
        initPropertyDetails();
        initMeetingsListView();
        initButtonsAndText();
        mBinding.myPropPageActPb.hide();
//        getProperty();

    }

    private void initMeetingsListView() {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.meetings_header, mBinding.myPropPageActListView, false);

        CustomCalendarView calendar = header.findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setShowOverflowDate(false);

        mBinding.myPropPageActListView.addHeaderView(header, null, false);
        mBinding.myPropPageActListView.setAdapter(null);
    }


    private void initHeaderPager() {
        List<String> headerItems = new ArrayList<>();
//        headerItems.add("http://www.google.co.il");
        //todo : add 3d cover photo first

        if (curProperty.getAlbum() != null) {
            headerItems.addAll(curProperty.getAlbum());
        }
        pagerAdapter = new PropertyPageHeaderAdapter(this, headerItems);
        mBinding.myPropPageActViewPager.setAdapter(pagerAdapter);

        dotscount = pagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            mBinding.myPropPageActDotsLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

        mBinding.myPropPageActViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.myPropPageActHeaderTitle.setText(curProperty.getFullAddress());

        mBinding.myPropPageActAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("wowOffset", "" + verticalOffset);
                if (verticalOffset < -400) {
                    Log.d("wowOffset", "fadeOut");
                    MyAnimationUtils.fadeOut(mBinding.myPropPageActDotsLayout, null);
                    MyAnimationUtils.fadeIn(mBinding.myPropPageActHeaderTitle);
                } else if (verticalOffset > -400) {
                    Log.d("wowOffset", "fadeIn");
                    MyAnimationUtils.fadeOut(mBinding.myPropPageActHeaderTitle, new Action() {
                        @Override
                        public void execute() {
                            MyAnimationUtils.fadeIn(mBinding.myPropPageActDotsLayout);
                        }
                    });
                }
            }
        });

    }

    private void initPropertyDetails() {
        mBinding.myPropPageActTitle.setText(curProperty.getFullAddress());
        mBinding.myPropPageActAddress.setText(curProperty.getAddress().getCityName());
        mBinding.myPropPageActViews.setText(curProperty.getViewCount() + " views");
        mBinding.myPropPageActLikes.setText(curProperty.getLikesCount() + " likes");

        loadDaysAdapter(false);
    }

    private void loadDaysAdapter(boolean withDelete) {
        mBinding.myPropPageActDaysRecyclerView.removeItemDecoration(daysItemDecoration);
        daysItemDecoration = null;
        if(withDelete){
            daysItemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset_edit);
        }else{
            daysItemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset);
        }
        mBinding.myPropPageActDaysRecyclerView.addItemDecoration(daysItemDecoration);
        daysAdapter = new OpenHouseDaysAdapter(curProperty.getOpenHouse(), getOnDayClickAction(), withDelete);
        mBinding.myPropPageActDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.myPropPageActDaysRecyclerView.setAdapter(daysAdapter);
        loadHoursAdapter(withDelete);
    }

    private void loadHoursAdapter(boolean withDelete) {
        mBinding.myPropPageActHoursRecyclerView.removeItemDecoration(hoursItemDecoration);
        hoursItemDecoration = null;
        if(withDelete){
            hoursItemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset_edit);
        }else{
            hoursItemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset);
        }
        hoursAdapter = new OpenHouseHoursAdapter(null, null, withDelete);
        mBinding.myPropPageActHoursRecyclerView.addItemDecoration(hoursItemDecoration);
        mBinding.myPropPageActHoursRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.myPropPageActHoursRecyclerView.setAdapter(hoursAdapter);
    }

    private TAction<OpenHouse> getOnDayClickAction() {
        return new TAction<OpenHouse>() {
            @Override
            public void execute(OpenHouse openHouse) {
                mBinding.myPropPageActHoursRecyclerView.setVisibility(View.VISIBLE);
                hoursAdapter.updateItems(openHouse.getSlots());
            }
        };
    }

    private void initButtonsAndText() {
        mBinding.myPropPageActViewHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyPageActivity.startWithProperty(MyPropertyPageActivity.this, curProperty);
                finish();
            }
        });

        mBinding.myPropPageActViewAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBinding.myPropPageActEditDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected = new ArrayList<OpenHouse>();
                mBinding.myPropPageActEditDays.setVisibility(View.GONE);
                mBinding.myPropertyPageActChangeHoursLayout.setVisibility(View.VISIBLE);
                loadDaysAdapter(true);
            }
        });

        mBinding.myPropPageActCancelHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected = null;
                mBinding.myPropPageActEditDays.setVisibility(View.VISIBLE);
                mBinding.myPropertyPageActChangeHoursLayout.setVisibility(View.GONE);
                loadDaysAdapter(false);
            }
        });

        mBinding.myPropPageActSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected();
            }
        });
    }

    private void deleteSelected() {
        String daysSelectedCsv = daysAdapter.getDeleted();
        String hoursSelectedCsv = hoursAdapter.getDeleted();
        daysAdapter.deleteDeleted();
        hoursAdapter.deleteDeleted();
        final Handler handler = new Handler();
        new ApiDeleteOpenHouseSlot(this).request(curProperty.getId().intValue(), daysSelectedCsv, hoursSelectedCsv, new Action() {
            @Override
            public void execute() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        daysAdapter.clearDeleted();
                        hoursAdapter.clearDeleted();
                        deleteSelected = null;
                        mBinding.myPropPageActEditDays.setVisibility(View.VISIBLE);
                        mBinding.myPropertyPageActChangeHoursLayout.setVisibility(View.GONE);
                        loadDaysAdapter(false);
                    }
                });
            }
        }, new Action() {
            @Override
            public void execute() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        daysAdapter.restoreDeleted();
                        hoursAdapter.restoreDeleted();
                    }
                });
            }
        });

    }

    public static void startWithProperty(Context context, Property property) {
        Intent intent = new Intent(context, MyPropertyPageActivity.class);
        propertyStat = property;
        context.startActivity(intent);
    }
}
