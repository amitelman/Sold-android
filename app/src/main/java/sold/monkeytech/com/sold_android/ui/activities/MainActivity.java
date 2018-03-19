package sold.monkeytech.com.sold_android.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityMainBinding;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.ui.fragments.MySoldFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SearchFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SearchInMapFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SellFragment;
import sold.monkeytech.com.sold_android.ui.fragments.ServiceFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SettingsFragment;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, MySoldFragment.MySoldListener, SearchInMapFragment.OnMapFragmentListener {

    private static final String SEARCH_FRAGMENT = "searchFragment";
    private static final String SERVICE_FRAGMENT = "serviceFragment";
    private static final String SELL_FRAGMENT = "sellFragment";
    private static final String MY_SOLD_FRAGMENT = "mySoldFragment";

    private static final String SETTINGS_FRAGMENT = "settingFragment";

    private ActivityMainBinding mBinding;
    private BaseFragment currentFrag;
    private String currentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initBottomBar();
        loadFragment(SEARCH_FRAGMENT);

    }

    private void initBottomBar() {
        mBinding.mainActBBSearchBtn.setOnClickListener(this);
        mBinding.mainActBBServiceBtn.setOnClickListener(this);
        mBinding.mainActBBSellBtn.setOnClickListener(this);
        mBinding.mainActBBMySoldBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActBBSearchBtn:
                loadFragment(SEARCH_FRAGMENT);
                break;
            case R.id.mainActBBServiceBtn:
                loadFragment(SERVICE_FRAGMENT);
                break;
            case R.id.mainActBBSellBtn:
                loadFragment(SELL_FRAGMENT);
                break;
            case R.id.mainActBBMySoldBtn:
                loadFragment(MY_SOLD_FRAGMENT);
                break;
        }
    }

//    public void loadFragment(int fragment) {
//        loadFragment(fragment, null);
//    }

    public void loadFragment(String fragmentType) {//}, final Bundle bundle) {
        switch (fragmentType) {
            case SEARCH_FRAGMENT:
                setFragmentTransition(new SearchFragment(), fragmentType);
                break;
            case SERVICE_FRAGMENT:
                setFragmentTransition(new ServiceFragment(), fragmentType);
                break;
            case SELL_FRAGMENT:
                setFragmentTransition(new SellFragment(), fragmentType);
                break;
            case MY_SOLD_FRAGMENT:
                setFragmentTransition(new MySoldFragment(), fragmentType);
                break;
            case SETTINGS_FRAGMENT:
                setFragmentTransition(new SettingsFragment(), fragmentType);
                break;
        }
    }

    private void setFragmentTransition(BaseFragment chosenFrag, String tag) {
        setFragmentTransition(chosenFrag, tag, null);
    }

    private void setFragmentTransition(BaseFragment chosenFrag, String tag, Bundle bundle) {
        if (tag.equals(currentTag)) {
//            handlePb(false);
            return;
        }
        if (bundle != null)
            chosenFrag.setArguments(bundle);
        currentFrag = chosenFrag;
        currentTag = tag;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.addToBackStack(null);
        ft.replace(R.id.mainActContainer, chosenFrag, tag);
        ft.commit();

        Log.d("wow", "MainAct - loading success : " + tag);
    }


    @Override
    public void MySoldFragmentAction(int action) {
        switch (action) {
            case MySoldFragment.SETTINGS:
                loadFragment(SETTINGS_FRAGMENT);
                break;
            case MySoldFragment.LOGIN:

                break;
            case MySoldFragment.LOGOUT:

                break;
            case MySoldFragment.FAVORITES:

                break;
            case MySoldFragment.SAVED:

                break;
            case MySoldFragment.MY_HOME:

                break;
        }
    }

    @Override
    public void onMarkerClick(Property property) {
        if (currentTag == SEARCH_FRAGMENT) {
            if (property != null) {
                ((SearchFragment) currentFrag).setBottomItemAndShow(property);
            }else{
                ((SearchFragment) currentFrag).hideBottomItem();
            }
        }
    }
}

