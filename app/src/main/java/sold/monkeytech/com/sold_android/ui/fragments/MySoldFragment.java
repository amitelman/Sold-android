package sold.monkeytech.com.sold_android.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentMySoldBinding;
import sold.monkeytech.com.sold_android.ui.activities.FavoriteActivity;
import sold.monkeytech.com.sold_android.ui.activities.MainActivity;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySoldFragment extends BaseFragment {

    public static final int SETTINGS = 0;
    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    public static final int FAVORITES = 3;
    public static final int SAVED = 4;
    public static final int MY_HOME = 5;

    private FragmentMySoldBinding mBinding;
    private MySoldListener listener;

    public MySoldFragment() {
        // Required empty public constructor
    }

    public interface MySoldListener{
        void MySoldFragmentAction(int action);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity) {
            listener = (MainActivity) context;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_sold, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        mBinding.mySoldActSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MySoldFragmentAction(SETTINGS);
            }
        });

        mBinding.mySoldActFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                startActivity(intent);
;            }
        });
    }
}

