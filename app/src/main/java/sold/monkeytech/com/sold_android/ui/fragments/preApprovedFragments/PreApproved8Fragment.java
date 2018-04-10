package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved8Binding;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.serverapi.auth.ApiGetCode;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved8Fragment extends BaseFragment {


    private FragmentPreApproved8Binding mBinding;
    public On8Listener listener;

    public PreApproved8Fragment() {
        // Required empty public constructor
    }

    public interface On8Listener{
        void onFrag8();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof PreApprovedActivity) {
            listener = (PreApprovedActivity) context;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved8, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        mBinding.preApproved8ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserManager.getInstance().getCurrentUser() == null){
                    mBinding.preApproved8Pb.show();
                    String first = mBinding.preApproved8First.getText().toString();
                    String last = mBinding.preApproved8Last.getText().toString();
                    String email = mBinding.preApproved8Email.getText().toString();
                    String phone = mBinding.preApproved8Phone.getText().toString();
                    final Handler handler = new Handler();
                    new ApiGetCode(getContext()).request(phone, first, last, email, UserManager.getInstance().getValidationDialog(getContext(), phone, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBinding.preApproved8Pb.hide();
                                    listener.onFrag8();
                                }
                            });
                        }
                    }), new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBinding.preApproved8Pb.hide();

                                }
                            });
                        }
                    });
                }else{
                    listener.onFrag8();
                }
            }
        });
    }

}
