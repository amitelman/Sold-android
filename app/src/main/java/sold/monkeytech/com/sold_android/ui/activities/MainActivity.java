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
import sold.monkeytech.com.sold_android.ui.fragments.MySoldFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SearchFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SellFragment;
import sold.monkeytech.com.sold_android.ui.fragments.ServiceFragment;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String SEARCH_FRAGMENT = "searchFragment";
    private static final String SERVICE_FRAGMENT = "serviceFragment";
    private static final String SELL_FRAGMENT = "sellFragment";
    private static final String MY_SOLD_FRAGMENT = "mySoldFragment";

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
        switch (view.getId()){
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

    public void loadFragment(String fragmentType){//}, final Bundle bundle) {
        switch (fragmentType) {
            case SEARCH_FRAGMENT:
                setFragmentTransition(new SearchFragment(), fragmentType);
//                setMidActionBar("MID_ICON", MidActionBar.ADD_MEMBER_BTN);
//                showBottomBar(true);
//                updateBottomBar("Clear", "Search", null, null);

                break;
            case SERVICE_FRAGMENT:
                setFragmentTransition(new ServiceFragment(), fragmentType);
//                setMidActionBar("Internal Orders", MidActionBar.ADD_BTN);
//                showBottomBar(true);
                break;
            case SELL_FRAGMENT:
                setFragmentTransition(new SellFragment(), fragmentType);
//                setMidActionBar("Members", MidActionBar.ADD_MEMBER_BTN);
//                showBottomBar(false);
                break;
            case MY_SOLD_FRAGMENT:
                setFragmentTransition(new MySoldFragment(), fragmentType);
//                setMidActionBar("QR Search", -1);
//                showBottomBar(true);
                break;
        }
    }

    private void setFragmentTransition(BaseFragment chosenFrag, String tag) {
        setFragmentTransition(chosenFrag, tag, null);
    }

    private void setFragmentTransition(BaseFragment chosenFrag, String tag, Bundle bundle) {
        if(tag.equals(currentTag)){
//            handlePb(false);
            return;
        }
        if(bundle != null)
            chosenFrag.setArguments(bundle);
        currentFrag = chosenFrag;
        currentTag = tag;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.addToBackStack(null);
        ft.replace(R.id.mainActContainer, chosenFrag, tag);
        ft.commit();

        Log.d("wow","MainAct - loading success : " + tag);
    }


}

