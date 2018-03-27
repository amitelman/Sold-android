package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityMainBinding;
import sold.monkeytech.com.sold_android.databinding.ActivityPreApprovedBinding;
import sold.monkeytech.com.sold_android.ui.fragments.MySoldFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SearchFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SellFragment;
import sold.monkeytech.com.sold_android.ui.fragments.ServiceFragment;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved10Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved11Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved1Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved2Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved3Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved4Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved5Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved6Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved7Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved8Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApproved9Fragment;
import sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments.PreApprovedStartFragment;

public class PreApprovedActivity extends BaseActivity implements PreApprovedStartFragment.OnStartListener, PreApproved1Fragment.On1Listener, PreApproved2Fragment.On2Listener, PreApproved3Fragment.On3Listener, PreApproved4Fragment.On4Listener, PreApproved5Fragment.On5Listener, PreApproved6Fragment.On6Listener, PreApproved7Fragment.On7Listener, PreApproved8Fragment.On8Listener, PreApproved9Fragment.On9Listener, PreApproved10Fragment.On10Listener, PreApproved11Fragment.On11Listener {

    private final String START = "start";
    private final String FRAG1 = "frag1";
    private final String FRAG2 = "frag2";
    private final String FRAG3 = "frag3";
    private final String FRAG4 = "frag4";
    private final String FRAG5 = "frag5";
    private final String FRAG6 = "frag6";
    private final String FRAG7 = "frag7";
    private final String FRAG8 = "frag8";
    private final String FRAG9 = "frag9";
    private final String FRAG10 = "frag10";
    private final String FRAG11 = "frag11";

    private ActivityPreApprovedBinding mBinding;
    private BaseFragment currentFrag;
    private String currentTag;

    //submit params
    private int purchaseType;
    private int loanAmount;
    private int employmentStatus;
    private String occupation;
    private int seniority;
    private int monthlyIncome;
    private int childCount;
    private int disposableIncome;
    private String otherIncome;
    private int fixesExpanses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pre_approved);

        loadFragment(START);
        initUi();
    }

    private void initUi() {
        mBinding.preApprovedActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadFragment(String fragmentType){//}, final Bundle bundle) {
        switch (fragmentType) {
            case START:
                setFragmentTransition(new PreApprovedStartFragment(), fragmentType);
                break;
            case FRAG1:
                setFragmentTransition(new PreApproved1Fragment(), fragmentType);
                break;
            case FRAG2:
                setFragmentTransition(new PreApproved2Fragment(), fragmentType);
                break;
            case FRAG3:
                setFragmentTransition(new PreApproved3Fragment(), fragmentType);
                break;
            case FRAG4:
                setFragmentTransition(new PreApproved4Fragment(), fragmentType);
                break;
            case FRAG5:
                setFragmentTransition(new PreApproved5Fragment(), fragmentType);
                break;
            case FRAG6:
                setFragmentTransition(new PreApproved6Fragment(), fragmentType);
                break;
            case FRAG7:
                setFragmentTransition(new PreApproved7Fragment(), fragmentType);
                break;
            case FRAG8:
                setFragmentTransition(new PreApproved8Fragment(), fragmentType);
                break;
            case FRAG9:
                setFragmentTransition(new PreApproved9Fragment(), fragmentType);
                break;
            case FRAG10:
                setFragmentTransition(new PreApproved10Fragment(), fragmentType);
                break;
            case FRAG11:
                setFragmentTransition(new PreApproved11Fragment(), fragmentType);
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
        ft.replace(R.id.preApprovedActContainer, chosenFrag, tag);
        ft.commit();
        Log.d("wow","MainAct - loading success : " + tag);
    }

    @Override
    public void onStartFrag() {
        loadFragment(FRAG1);
    }

    @Override
    public void onFrag1(int purchaseType) {
        this.purchaseType = purchaseType;
        loadFragment(FRAG2);
    }

    @Override
    public void onFrag2(int loanAmount) {
        //todo: should add equity??
        this.loanAmount = loanAmount;
        loadFragment(FRAG3);
    }

    @Override
    public void onFrag3(int employmentStatus) {
        this.employmentStatus = employmentStatus;
        loadFragment(FRAG4);
    }

    @Override
    public void onFrag4(String occupation) {
        this.occupation = occupation;
        loadFragment(FRAG5);
    }

    @Override
    public void onFrag5(int seniority) {
        this.seniority = seniority;
        loadFragment(FRAG6);
    }

    @Override
    public void onFrag6(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        loadFragment(FRAG7);
    }

    @Override
    public void onFrag7(int childCount) {
        this.childCount = childCount;
        loadFragment(FRAG8);
    }

    @Override
    public void onFrag8(int disposableIncome) {
        this.disposableIncome = disposableIncome;
        loadFragment(FRAG9);
    }

    @Override
    public void onFrag9(String otherIncome) {
        this.otherIncome = otherIncome;
        loadFragment(FRAG10);
    }

    @Override
    public void onFrag10(int fixesExpanses) {
        this.fixesExpanses = fixesExpanses;
        loadFragment(FRAG11);
    }

    @Override
    public void onFrag11() {
        sendInquiry();
    }

    private void sendInquiry() {
        //todo: send inquiry
        finish();
    }
}
