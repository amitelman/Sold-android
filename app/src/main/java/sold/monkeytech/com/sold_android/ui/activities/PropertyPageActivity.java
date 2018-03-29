package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityPropertyPageBinding;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseDaysAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseHoursAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PicturesSliderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.utils.ItemOffsetDecoration;

public class PropertyPageActivity extends BaseActivity {

    private ActivityPropertyPageBinding mBinding;
    private PicturesSliderAdapter pagerAdapter;
    private int dotscount;
    private ImageView[] dots;
    private OpenHouseHoursAdapter hoursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_property_page);

        initUi();
    }

    private void initUi() {

        initHeaderPager();
        getProperty();
    }

    private void getProperty() {
        final Handler handler = new Handler();
        new ApiGetPropertyById(this).request(5, new TAction<Property>() {
            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initProperty(property);
                    }
                });
            }
        }, null);
    }

    private void initProperty(Property property) {
        mBinding.propertyActTitle.setText(property.getAddress().getStreetName());
        mBinding.propertyActPrice.setText(property.getPrice().getFormatted());
        mBinding.propertyActAddress.setText(property.getAddress().getCityName() + " ");
        mBinding.propertyActRoomsCounter.setText(property.getRoomsCount() + "");
        mBinding.propertyActBathCounter.setText(property.getBathroomCount() + "");
        mBinding.propertyActSize.setText(property.getPlotArea() + " Sqm");
        mBinding.propertyActSqrm.setText(property.getFloorArea() + " Sqm");

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset);
        mBinding.propertyActDaysRecyclerView.setAdapter(new OpenHouseDaysAdapter(property.getOpenHouse(), getOnDayClickAction()));
        mBinding.propertyActDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.propertyActDaysRecyclerView.addItemDecoration(itemDecoration);

        hoursAdapter = new OpenHouseHoursAdapter(null, getOnHourClickAction());
        mBinding.propertyActHoursRecyclerView.setAdapter(hoursAdapter);
        mBinding.propertyActHoursRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.propertyActHoursRecyclerView.addItemDecoration(itemDecoration);

        mBinding.propertyActStatus.setText(property.getPropertyStatus());
        mBinding.propertyActPricePSq.setText(property.getMeterPrice().getFormatted());
        mBinding.propertyActType.setText(property.getPropertyType().getName());
        mBinding.propertyActBuild.setText(property.getBuiltAt() + "");
        mBinding.propertyActParking.setText(property.getParkingSlot()  + "");
        mBinding.propertyActTama.setText("???"); //todo : fill this
        mBinding.propertyActDescription.setText(property.getDescription());

        mBinding.propertyActReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private TAction<OpenHouse> getOnDayClickAction() {
        return new TAction<OpenHouse>() {
            @Override
            public void execute(OpenHouse openHouse) {
//                Toast.makeText(PropertyPageActivity.this, "wow", Toast.LENGTH_SHORT).show();
                hoursAdapter.updateItems(openHouse.getSlots());
                mBinding.propertyActRequestAShow.setAlpha(0.5f);
                mBinding.propertyActRequestAShow.setClickable(false);
            }
        };
    }

    private TAction<OpenHouseSlots> getOnHourClickAction() {
        return new TAction<OpenHouseSlots>() {
            @Override
            public void execute(OpenHouseSlots openHouseSlots) {
                if(openHouseSlots != null){
                    mBinding.propertyActRequestAShow.setAlpha(1);
                    mBinding.propertyActRequestAShow.setClickable(true);
                }else{
                    mBinding.propertyActRequestAShow.setAlpha(0.5f);
                    mBinding.propertyActRequestAShow.setClickable(false);
                }
            }
        };
    }


    private void initHeaderPager() {
        pagerAdapter = new PicturesSliderAdapter(this);
        mBinding.propertyActViewPager.setAdapter(pagerAdapter);

        dotscount = pagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            mBinding.propertyActDotsLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

        mBinding.propertyActViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.propertyActAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("wowOffset","" + verticalOffset);
                if(verticalOffset < -400){
                    Log.d("wowOffset","fadeOut");
                    MyAnimationUtils.fadeOut(mBinding.propertyActDotsLayout);
                    MyAnimationUtils.fadeOut(mBinding.propertyAct3dLayout);
                    MyAnimationUtils.fadeOut(mBinding.propertyActBtnsLayout);
                }else if(verticalOffset > -400){
                    Log.d("wowOffset","fadeIn");
                    MyAnimationUtils.fadeIn(mBinding.propertyActDotsLayout);
                    MyAnimationUtils.fadeIn(mBinding.propertyAct3dLayout);
                    MyAnimationUtils.fadeIn(mBinding.propertyActBtnsLayout);
                }
            }
        });

        mBinding.propertyActPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.propertyActPhotosBtnLayout.setSelected(true);
                mBinding.propertyActPhotosBtn.setSelected(true);
                mBinding.propertyActMapViewBtnLayout.setSelected(false);
                mBinding.propertyActMapViewBtn.setSelected(false);
            }
        });
        mBinding.propertyActMapViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.propertyActPhotosBtnLayout.setSelected(false);
                mBinding.propertyActPhotosBtn.setSelected(false);
                mBinding.propertyActMapViewBtnLayout.setSelected(true);
                mBinding.propertyActMapViewBtn.setSelected(true);
            }
        });

        mBinding.propertyActPhotosBtnLayout.setSelected(true);
        mBinding.propertyActPhotosBtn.setSelected(true);
        mBinding.propertyActMapViewBtnLayout.setSelected(false);
        mBinding.propertyActMapViewBtn.setSelected(false);
    }


}
