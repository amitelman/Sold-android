package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityPropertyPageBinding;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseDaysAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseHoursAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PicturesSliderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyFeaturesImagesAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.utils.ItemOffsetDecoration;

public class PropertyPageActivity extends BaseActivity {

    private ActivityPropertyPageBinding mBinding;
    private PicturesSliderAdapter pagerAdapter;
    private int dotscount;
    private ImageView[] dots;
    private OpenHouseHoursAdapter hoursAdapter;
    private boolean descriptionExpanded = false;
    private PropertyFeaturesImagesAdapter imagesAdapter;

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
                        initPropertyDetails(property);
                        initPropertyFeatures(property);
                    }
                });
            }
        }, null);
    }

    private void initPropertyDetails(Property property) {
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
                    ViewGroup.LayoutParams params = mBinding.propertyActDescription.getLayoutParams();
                if(!descriptionExpanded){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    mBinding.propertyActDescription.setLayoutParams(params);
                    descriptionExpanded = true;
                }else{
                    params.height = TextUtils.dpToPx(70);
                    mBinding.propertyActDescription.setLayoutParams(params);
                    descriptionExpanded = false;
                    mBinding.propertyActDescription.setLines(4);
                }
            }
        });
    }

    private void initPropertyFeatures(Property property) {
        for(PropertyFeatures feature : property.getPropertyFeatures()){
            View child = getLayoutInflater().inflate(R.layout.property_feature_item, null);
            TextView title = child.findViewById(R.id.featureItemTitle);
            TextView description = child.findViewById(R.id.featureItemValue);
            RecyclerView imagesView = child.findViewById(R.id.featureItemList);
            title.setText("• " + feature.getFeatureName());

            final SpannableStringBuilder descFinal = new SpannableStringBuilder("");
            final SpannableStringBuilder desc = new SpannableStringBuilder("");
            for(Meta m : feature.getMeta()){
                desc.append(m.getKey() + ": " + m.getValue() + ", ");

                final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
                desc.setSpan(bss, m.getKey().length() + 2, (m.getKey().length() + 2) + m.getValue().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                descFinal.append(desc);
                desc.clear();
            }
            description.setText(descFinal.subSequence(0, descFinal.length() - 2));

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            imagesView.setLayoutManager(layoutManager);
            imagesAdapter = new PropertyFeaturesImagesAdapter(this, feature.getImages());
            imagesView.setAdapter(imagesAdapter);

            mBinding.propertyFeaturesActList.addView(child);
        }
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
