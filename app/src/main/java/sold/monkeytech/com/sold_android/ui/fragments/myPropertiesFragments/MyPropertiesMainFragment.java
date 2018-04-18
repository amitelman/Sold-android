package sold.monkeytech.com.sold_android.ui.fragments.myPropertiesFragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentMyPropertiesMainBinding;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiGetMyProperties;
import sold.monkeytech.com.sold_android.ui.activities.MainActivity;
import sold.monkeytech.com.sold_android.ui.fontable_views.FontableTextView;
import sold.monkeytech.com.sold_android.ui.fragments.SearchFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SellFragment;
import sold.monkeytech.com.sold_android.ui.fragments.ServiceFragment;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPropertiesMainFragment extends BaseFragment {

    public final String MY_PROPERTIES_FRAGMENT = "myPropFrag";
    public final String PENDING_FRAGMENT = "pendingFrag";
    private FragmentMyPropertiesMainBinding mBinding;
    private String currentTag = "";
    private BaseFragment currentFrag;

    public List<Property> pendingProperties;
    public List<Property> approvedProperties;

    public MyPropertiesMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_properties_main, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        mBinding.myPropMainFragSellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadSellFrag();
            }
        });

        mBinding.myPropMainFragMyPropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedBtns(mBinding.myPropMainFragMyPropBtn, mBinding.myPropMainFragPendingPropBtn);
                loadFragment(MY_PROPERTIES_FRAGMENT);
            }
        });

        mBinding.myPropMainFragPendingPropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedBtns(mBinding.myPropMainFragPendingPropBtn, mBinding.myPropMainFragMyPropBtn);
                loadFragment(PENDING_FRAGMENT);
            }
        });



        final Handler handler = new Handler();
        mBinding.myPropMainFragPb.show();
        new ApiGetMyProperties(getContext()).request(new TAction<List<Property>>() {
            @Override
            public void execute(final List<Property> properties) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.myPropMainFragPb.hide();
                        initFragments(properties);

                    }
                });
            }
        }, new TAction<String>() {
            @Override
            public void execute(String s) {

            }
        });
    }

    private void initFragments(List<Property> properties) {
        pendingProperties = new ArrayList<>();
        approvedProperties = new ArrayList<>();
        for(Property p : properties){
            if(p.getPropertyStatus().equals("pending"))
                pendingProperties.add(p);
            else
                approvedProperties.add(p);
        }
        if(approvedProperties.size() == 0){
            setSelectedBtns(mBinding.myPropMainFragPendingPropBtn, mBinding.myPropMainFragMyPropBtn);
            loadFragment(PENDING_FRAGMENT);
        }else{
            setSelectedBtns(mBinding.myPropMainFragMyPropBtn, mBinding.myPropMainFragPendingPropBtn);
            loadFragment(MY_PROPERTIES_FRAGMENT);
        }
    }

    private void setSelectedBtns(TextView selected, TextView unSelected) {
        selected.setSelected(true);
        unSelected.setSelected(false);
    }

    public void loadFragment(String fragmentType) {
        switch (fragmentType) {
            case MY_PROPERTIES_FRAGMENT:
                setFragmentTransition(new MyPropertiesListFragment().setProperties(approvedProperties), fragmentType);
                break;
            case PENDING_FRAGMENT:
                setFragmentTransition(new MyPendingPropertiesFragment().setProperties(pendingProperties), fragmentType);
                break;
        }
    }


    private void setFragmentTransition(BaseFragment chosenFrag, String tag) {
        if (tag.equals(currentTag)) {
//            handlePb(false);
            return;
        }
        currentFrag = chosenFrag;
        currentTag = tag;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.addToBackStack(null);
        ft.replace(R.id.myPropMainFragContainer, chosenFrag, tag);
        ft.commit();

        Log.d("wow", "MainAct - loading success : " + tag);
    }



}
