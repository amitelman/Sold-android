package sold.monkeytech.com.sold_android.ui.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.ui.activities.BaseActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityTest3Binding;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.managers.TestManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.ui.adapters.PicturesSliderAdapter;

public class Test3Activity extends BaseActivity {

    private ActivityTest3Binding mBinding;
    private PicturesSliderAdapter pagerAdapter;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test3);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test3);


//        String shortListP = TestManager.getInstance().getshortPropList();
//        try {
//            JSONArray propertyArr = new JSONArray(shortListP);
//            List<Property> propertyList = new PropertyParser(PropertyParser.TYPE_SHORT).parseToList(propertyArr);
//            Log.d("wow", "wow");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String longP = TestManager.getInstance().getLongProperty();
//        try {
//            JSONObject longProperty = new JSONObject(longP);
//            Property property = new PropertyParser(PropertyParser.TYPE_FULL).parseToSingle(longProperty);
//            Log.d("wow", "wow");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }

        initUi();


    }

    private void initUi() {


        initHeaderPager();
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
