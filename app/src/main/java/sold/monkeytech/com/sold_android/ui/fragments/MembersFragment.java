//package sold.monkeytech.com.sold_android.ui.fragments;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import sold.monkeytech.com.sold_android.R;
//import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;
//
//
//public class MembersFragment extends BaseFragment implements View.OnClickListener {
//
//    private ViewPager pager;
//
//    public MembersFragment() {
//
//    }
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_members, container, false);
//        return rootView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        initPager();
//    }
//
//    private void initPager() {
//        final TextView allMembersBtn = (TextView) getView().findViewById(R.id.membersFragAllPage);
//        final TextView pendingMembersBtn = (TextView) getView().findViewById(R.id.membersFragPendingPage);
//        final FrameLayout allMembersBtnLayout = (FrameLayout) getView().findViewById(R.id.membersFragAllLayout);
//        final FrameLayout pendingMembersBtnLayout = (FrameLayout) getView().findViewById(R.id.membersFragPendingLayout);
//
////        screenWidth = DpUtils.getPixelsFromDP(getActivity(), 130);
//
//        pager = (ViewPager) getView().findViewById(R.id.MembersFragViewPager);
//        pager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager()));
//
//        allMembersBtnLayout.setOnClickListener(this);
//        pendingMembersBtnLayout.setOnClickListener(this);
//
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    changeIndicator(allMembersBtnLayout);
//                    changeTabsUi(allMembersBtn, pendingMembersBtn);
//                } else if (position == 1) {
//                    changeIndicator(pendingMembersBtnLayout);
//                    changeTabsUi(pendingMembersBtn, allMembersBtn);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
////        setDefaultFrag();
//    }
//
//    private void changeTabsUi(TextView selectedView, TextView unselectedView) {
//        selectedView.setAlpha(1f);
//        selectedView.setTextColor(getResources().getColor(R.color.turquoise));
//        KeyboardAndTextUtils.getCustomTextView(selectedView, KeyboardAndTextUtils.TURQUISE, KeyboardAndTextUtils.BOLD, -1);
//        unselectedView.setAlpha(0.5f);
//        unselectedView.setTextColor(getResources().getColor(R.color.warm_grey));
//        KeyboardAndTextUtils.getCustomTextView(unselectedView, KeyboardAndTextUtils.WARM_GREY, KeyboardAndTextUtils.REG, -1);
//    }
//
//    private void setDefaultFrag() {
//        final FrameLayout allMembersBtnLayout = (FrameLayout) getView().findViewById(R.id.membersFragAllLayout);
//        pager.setCurrentItem(0);
//        changeIndicator(allMembersBtnLayout);
//    }
//
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.membersFragAllLayout:
//                pager.setCurrentItem(0);
//                break;
//
//            case R.id.membersFragPendingLayout:
//                pager.setCurrentItem(1);
//                break;
//        }
//    }
//
//    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
//        Context context;
//
//        public ViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//
//            switch (position) {
//                default:
//                case 0:
//                    return new AllMembersFragment();
//                case 1:
//                    return new PendingMembersFragment();
//            }
//        }
//    }
//
//    public void changeIndicator(final View v) {
//        final View selectorIndicator = getView().findViewById(R.id.membersFragIndicator);
//        if (selectorIndicator.getAnimation() != null) {
//            selectorIndicator.clearAnimation();
//        }
//        float whereToGo = (v.getX() - selectorIndicator.getX()) / selectorIndicator.getWidth();
//        TranslateAnimation indicatorAnimator = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, whereToGo,
//                Animation.RELATIVE_TO_PARENT, 0.0f,
//                Animation.RELATIVE_TO_PARENT, 0.0f);
//
//        indicatorAnimator.setDuration(250);
//        indicatorAnimator.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                selectorIndicator.setX(v.getX());
//                if (selectorIndicator.getAnimation() != null)
//                    selectorIndicator.clearAnimation();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        selectorIndicator.startAnimation(indicatorAnimator);
//    }
//
//
//
//
//
//}
