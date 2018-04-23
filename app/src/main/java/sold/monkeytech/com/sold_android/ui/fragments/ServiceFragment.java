package sold.monkeytech.com.sold_android.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.List;
import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentServiceBinding;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;
import sold.monkeytech.com.sold_android.ui.activities.MainActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends BaseFragment {


    private FragmentServiceBinding mBinding;
    private MyPagerAdapter adapterViewPager;
    private int dotsCount;
    private ImageView[] dots;
    public List<ServicePage> servicePageList;
    private int currentPage;

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initUi();
    }

    private void initUi() {
        servicePageList = MetaDataManager.getInstance().getServicePages();
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        mBinding.myServiceActPager.setAdapter(adapterViewPager);

        dotsCount = servicePageList.size();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vp_red_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            mBinding.myServiceFragDotsLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vp_red_circle_selected));

        mBinding.myServiceActPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                Log.d("wowImageSlider","current: " + currentPage);
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vp_red_circle_unselected));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vp_red_circle_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.myServiceActSellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(MainActivity.SELL_FRAGMENT);
            }
        });
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = servicePageList.size();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return new ServiceItemFragment().setPage(servicePageList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

    }




}
