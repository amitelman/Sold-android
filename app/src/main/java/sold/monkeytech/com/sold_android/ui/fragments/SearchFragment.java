//package sold.monkeytech.com.sold_android.ui.fragments;
//
//
//import android.Manifest;
//import android.animation.ObjectAnimator;
//import android.content.Intent;
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import com.mid.mid.R;
//import com.mid.mid.databinding.FragmentSearchBinding;
//import com.mid.mid.framework.Utils.KeyboardAndTextUtils;
//import com.mid.mid.framework.Utils.MyAnimationUtils;
//import com.mid.mid.framework.Utils.PermissionUtils;
//import com.mid.mid.framework.Utils.TextUtils;
//import com.mid.mid.framework.managers.DataFiltersManager;
//import com.mid.mid.framework.managers.PagedQueryManager;
//import com.mid.mid.framework.managers.UserManager;
//import com.mid.mid.framework.models.SearchObject;
//import com.mid.mid.framework.models.member.Member;
//import com.mid.mid.framework.serverapi.stones.ApiSearch;
//import com.mid.mid.ui.activities.FindLotIdActivity;
//import com.mid.mid.ui.activities.FindMemberActivity;
//import com.mid.mid.ui.activities.FindMemoActivity;
//import com.mid.mid.ui.activities.FindTrayActivity;
//import com.mid.mid.ui.activities.MainActivity;
//import com.mid.mid.ui.activities.QRActivity;
//import com.mid.mid.ui.activities.ResultsActivity;
//import com.mid.mid.ui.dialogs.SimpleOneBtnDialog;
//import com.mid.mid.ui.dialogs.SimpleTwoBtnDialog;
//import com.mid.mid.ui.fragments.abs.BaseFragment;
//import com.monkeytechy.framework.interfaces.Action;
//import com.monkeytechy.framework.interfaces.TAction;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import static android.app.Activity.RESULT_OK;
//import static android.view.View.GONE;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class SearchFragment extends BaseFragment {
//
//    private static final int ADD_MEMBER_ACTIVITY = 00;
//    public static final int SEARCH_LOT_INTENT = 10;
//    private static final int FIND_TRAY_INTENT = 20;
//    private static final int FIND_MEMO_INTENT = 30;
//    private static final int QR_CODE_READER = 40;
//    private FragmentSearchBinding mBinding;
//    public static SearchFragment instance;
//    public final int TOTAL = 0;
//    public final int PPC = 1;
//    public final int RAP = 2;
//
//
//    public SearchFragment() {
//
//    }
//
//    public static SearchFragment getInstance(){
//        if(instance == null)
//            instance = new SearchFragment();
//        return instance;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        KeyboardAndTextUtils.closeKeyboard(getActivity());
//
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
//        View view = mBinding.getRoot();
//
//
//
//
//        if(!UserManager.getInstance().isMember())
//            resetAllSearchData();
//
//        return view;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(getView() != null && mBinding != null){
//            mBinding.searchFragScroll.fullScroll(ScrollView.FOCUS_UP);
//            if(!errorOcurred()){
//                initViews();
//                initFragUi();
//                initTopRightIcon();
//                initBundle();
//            }
//
//        }
//    }
//
//    private boolean errorOcurred() {
//        if(DataFiltersManager.getInstance().getShapes() == null || DataFiltersManager.getInstance().getShapes().size() == 0) {
//            new SimpleOneBtnDialog(getContext(), "Oops..", "Seems like something went wrong, Please try again Later", true, new Action() {
//                @Override
//                public void execute() {
//                    getActivity().finish();
//                    System.exit(0);
//                }
//            }).show();
//            return true;
//        }
//        return false;
//    }
//
//    private void initBundle() {
//        if (getArguments() != null) {
//            String qrStr = getArguments().getString("qrStr");
//            if(!TextUtils.isEmpty(qrStr))
//                qrSearch(qrStr);
//            setArguments(null);
//        }
//    }
//
//    private void initTopRightIcon() {
//        if(!UserManager.getInstance().isMember()){
//            if(PagedQueryManager.getInstance().getSelectedStoneList().size() > 0){
//                ((MainActivity)getActivity()).changeActionBarRightBtn(MainActivity.ACTION_BAR_MENU_BTN);
//
//            }else{
//                ((MainActivity)getActivity()).changeActionBarRightBtn(MainActivity.ACTION_BAR_ADD_MEMBER_BTN);
//            }
//        }else{
//            initMemberUi();
//        }
//    }
//
//    private void initMemberUi() {
//        ((MainActivity)getActivity()).changeActionBarRightBtn(MainActivity.MEMBER_DIAMOND);
//        mBinding.searchFragMultiOptionsStatus.setVisibility(GONE);
//        mBinding.searchFragMultiValueRangeView.setVisibility(GONE);
//        mBinding.searchFragRequirementsLayout.setVisibility(GONE);
//        mBinding.searchFragForMemberMainLayout.setVisibility(GONE);
//        mBinding.searchFragTrayLayout.setVisibility(GONE);
//
//        mBinding.searchFragMemoLayout.setVisibility(GONE);
//    }
//
//    private void initViews() {
//        if(mBinding != null){
//
//            updateOnBehalfOfMember();
//            mBinding.searchFragMultiShapes.init(DataFiltersManager.getInstance().getShapes());
//            if(UserManager.getInstance().isLastColorSelectedFancy()){
//                mBinding.searchFragColorMultiView.setColors(DataFiltersManager.getInstance().getFancyColorsIntensities());
//            }else{
//                mBinding.searchFragColorMultiView.setColors(DataFiltersManager.getInstance().getNormalColors());
//            }
//            mBinding.searchFragColorMultiView.setFancy(DataFiltersManager.getInstance().getFancyColors());
//            mBinding.searchFragMultiOptionsClarity.setOptions(DataFiltersManager.getInstance().getClarities());
//            mBinding.searchFragMultiOptionsCut.setOptions(DataFiltersManager.getInstance().getCutsLabels());
//            mBinding.searchFragMultiOptionsPolish.setOptions((DataFiltersManager.getInstance().getPolishLabels()));
//            mBinding.searchFragMultiOptionsSymmetry.setOptions((DataFiltersManager.getInstance().getSymmertyLabels()));
//            mBinding.searchFragMultiOptionsFluorescence.setOptions((DataFiltersManager.getInstance().getFluorescenceIntensities()));
//            mBinding.searchFragMultiOptionsLab.setOptions((DataFiltersManager.getInstance().getLabs()));
//            mBinding.searchFragMultiOptionsStatus.setOptions(DataFiltersManager.getInstance().getStatuses());
//            mBinding.searchFragMultiOptionsTreatments.setOptions(DataFiltersManager.getInstance().getTreatments());
//            mBinding.searchFragMultiOptionsAvailability.setOptions(DataFiltersManager.getInstance().getAvailabilities());
//            mBinding.searchFragMultiOptionsLocation.setOptions(DataFiltersManager.getInstance().getLocations());
//            mBinding.searchFragValueRangeDepth.setRange(DataFiltersManager.getInstance().getMinDepth(), DataFiltersManager.getInstance().getMaxDepth(), "%");
//            mBinding.searchFragValueRangeTable.setRange(DataFiltersManager.getInstance().getMinTable(), DataFiltersManager.getInstance().getMaxTable(), "%");
//            mBinding.searchFragValueRangeRatio.setRange(DataFiltersManager.getInstance().getMinRatio(), DataFiltersManager.getInstance().getMaxRatio(), "");
//
//        }
//    }
//
//    private void initFragUi() {
//        mBinding.searchFragAdvancedFiltersLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mBinding.searchFragAdvancedFiltersLayoutContent.getVisibility() == View.VISIBLE){
//                    MyAnimationUtils.collapse(mBinding.searchFragAdvancedFiltersLayoutContent, 300);
//                    MyAnimationUtils.rotateLeft(mBinding.searchFragAdvancedFiltersImg);
//                }else{
//                    MyAnimationUtils.expand(mBinding.searchFragAdvancedFiltersLayoutContent);
//                    MyAnimationUtils.rotateRight(mBinding.searchFragAdvancedFiltersImg);
//                    mBinding.searchFragScroll.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            ObjectAnimator.ofInt(mBinding.searchFragScroll, "scrollY",  mBinding.searchFragAdvancedFiltersLayout.getTop()).setDuration(250).start();
//                        }
//                    },550);
//                }
//            }
//        });
//
//        mBinding.searchFragForMemberLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), FindMemberActivity.class);
//                startActivityForResult(intent, ADD_MEMBER_ACTIVITY);
//            }
//        });
//
//        mBinding.searchFragOnBehalfMemberCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PagedQueryManager.getInstance().setOnBehalfOfMember(null);
//                updateOnBehalfOfMember();
//            }
//        });
//        mBinding.searchFragLotIdSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), FindLotIdActivity.class);
//                getActivity().startActivityForResult(intent, SEARCH_LOT_INTENT);
//            }
//        });
//
//        mBinding.searchFragInputCancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String str = mBinding.searchFragInputView.getText().toString();
//                if(str.length() > 0){
//                    new SimpleTwoBtnDialog(getContext(), "Full Reset", "Are you sure you would like to reset current search?", new Action() {
//                        @Override
//                        public void execute() {
//                            ((TextView)mBinding.searchFragInputView).setText("");
//                            mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.btn_qr);
//                            PagedQueryManager.getInstance().resetFreeText();
//                        }
//                    }, true).show();
//                }else{
//                    startQrReader();
//                }
//            }
//        });
//
//        mBinding.searchFragTrayLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), FindTrayActivity.class);
//                startActivityForResult(intent, FIND_TRAY_INTENT);
//            }
//        });
//
//        mBinding.searchFragMemoLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), FindMemoActivity.class);
//                startActivityForResult(intent, FIND_MEMO_INTENT);
//            }
//        });
//
//    }
//
//    private void startQrReader() {
//        if(PermissionUtils.checkPermissions(getContext(), PermissionUtils.CAMERA_PERMISSIONS)){
//            Intent intent = new Intent(getContext(), QRActivity.class);
//            startActivityForResult(intent, QR_CODE_READER);
//        }else{
//            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 123);
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            if(requestCode == ADD_MEMBER_ACTIVITY){
//                PagedQueryManager.getInstance().clearSelectedList();
//                PagedQueryManager.getInstance().updatePagedQueryObjectSearchId(null);
//                PagedQueryManager.getInstance().clearPagedQueryObjectSearch();
//                initTopRightIcon();
//                updateOnBehalfOfMember();
//            }
////            if(requestCode == SEARCH_LOT_INTENT){
////                String lotId = data.getStringExtra("lot");
////                Log.d("wow","lotId: " + lotId);
////                PagedQueryManager.getInstance().setFreeText(lotId);
////                mBinding.searchFragInputView.setText(lotId);
////                mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.cancel);
////                mBinding.searchFragInputView.addTextChangedListener(new TextWatcher() {
////                    @Override
////                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////                    }
////
////                    @Override
////                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                        if(charSequence.length() == 0){
////                            PagedQueryManager.getInstance().resetFreeText();
////                            mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.btn_qr);
////                        }
////
////                    }
////
////                    @Override
////                    public void afterTextChanged(Editable editable) {
////
////                    }
////                });
////            }
//            if(requestCode == FIND_TRAY_INTENT){
//                ArrayList<String> trays = data.getStringArrayListExtra("trayList");
//                Log.d("wow","woww");
//                String selectedStr = "";
//                for(String s : trays){
//                    selectedStr += s + ",";
//                }
//                PagedQueryManager.getInstance().updateQueryObject("Tray",selectedStr);
//                mBinding.searchFragTrayText.setText(selectedStr);
//                mBinding.searchFragTrayText.setTextColor(getResources().getColor(R.color.black));
//                mBinding.searchFragTrayCancel.setVisibility(View.VISIBLE);
//                mBinding.searchFragTrayCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mBinding.searchFragTrayText.setText("Search Trays...");
//                        mBinding.searchFragTrayText.setTextColor(getResources().getColor(R.color.grey));
//                        mBinding.searchFragTrayCancel.setVisibility(View.GONE);
//                        PagedQueryManager.getInstance().updateQueryObject("Tray",null);
//                    }
//                });
//            }
//
//            if(requestCode == FIND_MEMO_INTENT){
//                ArrayList<String> trays = data.getStringArrayListExtra("memoList");
//                String selectedStr = "";
//                for(String s : trays){
//                    selectedStr += s + ",";
//                }
//                PagedQueryManager.getInstance().updateQueryObject("MemoDestination",selectedStr);
//                mBinding.searchFragMemoText.setText(selectedStr);
//                mBinding.searchFragMemoText.setTextColor(getResources().getColor(R.color.black));
//                mBinding.searchFragMemoCancel.setVisibility(View.VISIBLE);
//                mBinding.searchFragMemoCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mBinding.searchFragMemoText.setText("Memo Destinations...");
//                        mBinding.searchFragMemoText.setTextColor(getResources().getColor(R.color.grey));
//                        mBinding.searchFragMemoCancel.setVisibility(View.GONE);
//                        PagedQueryManager.getInstance().updateQueryObject("MemoDestination",null);
//                    }
//                });
//            }
//            if(requestCode == QR_CODE_READER){
//                String contents = data.getStringExtra("qrData");
//                qrSearch(contents);
//            }
//        }
//    }
//
//    public void searchLotAction(String lotId) {
//        if(!TextUtils.isEmpty(lotId)){
//            Log.d("wowLotId","lotId: " + lotId);
//            PagedQueryManager.getInstance().setFreeText(lotId);
//            mBinding.searchFragInputView.setText(lotId);
//            mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.cancel);
//            mBinding.searchFragInputView.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if(charSequence.length() == 0){
//                        PagedQueryManager.getInstance().resetFreeText();
//                        mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.btn_qr);
//                    }
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//
//        }
//    }
//
//    private void updateOnBehalfOfMember() {
//        final Handler handler = new Handler();
//        PagedQueryManager.getInstance().getOnBehalfOfMember(getActivity(), new TAction<Member>() {
//            @Override
//            public void execute(final Member member) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(member != null){
//                            mBinding.searchFragOnBehalfMember.setText(member.getFirstName() + " " + member.getLastName());
////                            mBinding.searchFragOnBehalfMemberCancel.setVisibility(View.VISIBLE);
//                        }else{
//                            mBinding.searchFragOnBehalfMember.setText("");
//                            mBinding.searchFragOnBehalfMemberCancel.setVisibility(GONE);
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//    public void search(Action onDone) {
//        PagedQueryManager.getInstance().updateQueryObject("ShapeList",mBinding.searchFragMultiShapes.getSelectedShapes());
//        PagedQueryManager.getInstance().updateQueryObject("WeightFrom",mBinding.searchFragWeightValueRangeView.getFromValue());
//        PagedQueryManager.getInstance().updateQueryObject("WeightTo",mBinding.searchFragWeightValueRangeView.getToValue());
//        PagedQueryManager.getInstance().updateQueryObject("ColorType",mBinding.searchFragColorMultiView.getColorType());
//        PagedQueryManager.getInstance().updateQueryObject("ColorList",mBinding.searchFragColorMultiView.getSelectedColors());
//        if(mBinding.searchFragColorMultiView.getColorType().equals("Fancy")){
//            PagedQueryManager.getInstance().updateQueryObject("FancyIntensityList",mBinding.searchFragColorMultiView.getSelectedColors());
//            PagedQueryManager.getInstance().updateQueryObject("ColorList",mBinding.searchFragColorMultiView.getFancyIntensities());
//        }
//        PagedQueryManager.getInstance().updateQueryObject("ClarityList",mBinding.searchFragMultiOptionsClarity.getSelectedOptions());
//        PagedQueryManager.getInstance().updateQueryObject("CutList",mBinding.searchFragMultiOptionsCut.getSelectedLongStringOptions());
//        PagedQueryManager.getInstance().updateQueryObject("PolishList",mBinding.searchFragMultiOptionsPolish.getSelectedLongStringOptions());
//        PagedQueryManager.getInstance().updateQueryObject("SymmetryList",mBinding.searchFragMultiOptionsSymmetry.getSelectedLongStringOptions());
//        PagedQueryManager.getInstance().updateQueryObject("FluoresenceIntensityList",mBinding.searchFragMultiOptionsFluorescence.getSelectedOptions());
//        PagedQueryManager.getInstance().updateQueryObject("Labs",mBinding.searchFragMultiOptionsLab.getSelectedOptions());
//        PagedQueryManager.getInstance().updateQueryObject("Statuses",mBinding.searchFragMultiOptionsStatus.getSelectedOptions());
//        PagedQueryManager.getInstance().updateQueryObject("StoneAvailabilities",mBinding.searchFragMultiOptionsAvailability.getSelectedOptions());
//        PagedQueryManager.getInstance().updateQueryObject("BusinessRegions",mBinding.searchFragMultiOptionsLocation.getSelectedOptions());
//
//        switch (mBinding.searchFragMultiValueRangeView.getType()){
//            case TOTAL:
//                PagedQueryManager.getInstance().updateQueryObject("TotalPriceFrom", mBinding.searchFragMultiValueRangeView.getFromValue());
//                PagedQueryManager.getInstance().updateQueryObject("TotalPriceTo", mBinding.searchFragMultiValueRangeView.getToValue());
//                break;
//            case PPC:
//                PagedQueryManager.getInstance().updateQueryObject("PricePerCaratFrom", mBinding.searchFragMultiValueRangeView.getFromValue());
//                PagedQueryManager.getInstance().updateQueryObject("PricePerCaratTo", mBinding.searchFragMultiValueRangeView.getToValue());
//                break;
//            case RAP:
//                PagedQueryManager.getInstance().updateQueryObject("DiscountFrom", mBinding.searchFragMultiValueRangeView.getFromValue());
//                PagedQueryManager.getInstance().updateQueryObject("DiscountTo", mBinding.searchFragMultiValueRangeView.getToValue());
//                break;
//        }
//
//        PagedQueryManager.getInstance().updateQueryObject("DepthFrom", mBinding.searchFragValueRangeDepth.getFromValue());
//        PagedQueryManager.getInstance().updateQueryObject("DepthTo", mBinding.searchFragValueRangeDepth.getToValue());
//
//        PagedQueryManager.getInstance().updateQueryObject("TablePercentFrom", mBinding.searchFragValueRangeTable.getFromValue());
//        PagedQueryManager.getInstance().updateQueryObject("TablePercentTo", mBinding.searchFragValueRangeTable.getToValue());
//
//        PagedQueryManager.getInstance().updateQueryObject("RatioFrom", mBinding.searchFragValueRangeRatio.getFromValue());
//        PagedQueryManager.getInstance().updateQueryObject("RatioTo", mBinding.searchFragValueRangeRatio.getToValue());
//
//        PagedQueryManager.getInstance().updateQueryObject("WeightFrom", mBinding.searchFragWeightValueRangeView.getFromValue());
//        PagedQueryManager.getInstance().updateQueryObject("WeightTo", mBinding.searchFragWeightValueRangeView.getToValue());
//
//        PagedQueryManager.getInstance().updateQueryObject("OnHoldFlag", mBinding.searchFragYesNoAllOnHold.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("HideCustomerStonesFlag", mBinding.searchFragYesNoAllReserved.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("IsNewArrivalsOnly", mBinding.searchFragYesNoAllNewArrival.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("HideStoneFlag", mBinding.searchFragYesNoAllListed.getReversedSelected());
//        PagedQueryManager.getInstance().updateQueryObject("IsPair", mBinding.searchFragYesNoAllMatchingStone.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("IsMatchSeparable", mBinding.searchFragYesNoAllMatchingStone.getExtensionSelected());
//        PagedQueryManager.getInstance().updateQueryObject("HaveDiamondImage", mBinding.searchFragYesNoAllImage.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("HaveCertificateImage", mBinding.searchFragYesNoAllCertificate.getSelected());
//        PagedQueryManager.getInstance().updateQueryObject("HaveV360Video", mBinding.searchFragYesNoAll360.getSelected());
//
//        PagedQueryManager.getInstance().updateQueryObject("Treatment", mBinding.searchFragMultiOptionsTreatments.getSelectedOptions());
//
//
//        JSONObject jo = PagedQueryManager.getInstance().getUpdatedPagedQueryObject();
//        Log.d("wow","wow" + jo);
//        if(onDone != null)
//            onDone.execute();
//    }
//
//    public void resetSearchParam() {
//        mBinding.searchFragMultiShapes.deselect();
//        mBinding.searchFragWeightValueRangeView.deselect();
//        mBinding.searchFragColorMultiView.deselect();
//        mBinding.searchFragMultiOptionsClarity.deselect();
//        mBinding.searchFragMultiOptionsCut.deselect();
//        mBinding.searchFragMultiOptionsPolish.deselect();
//        mBinding.searchFragMultiOptionsSymmetry.deselect();
//        mBinding.searchFragMultiOptionsFluorescence.deselect();
//        mBinding.searchFragMultiOptionsLab.deselect();
//        mBinding.searchFragMultiOptionsStatus.deselect();
//        mBinding.searchFragMultiOptionsAvailability.deselect();
//        mBinding.searchFragMultiOptionsLocation.deselect();
//        mBinding.searchFragMultiValueRangeView.deselect();
//        mBinding.searchFragValueRangeDepth.deselect();
//        mBinding.searchFragValueRangeTable.deselect();
//        mBinding.searchFragValueRangeRatio.deselect();
//        mBinding.searchFragYesNoAllImage.deselect();
//        mBinding.searchFragYesNoAllCertificate.deselect();
//        mBinding.searchFragYesNoAll360.deselect();
//        mBinding.searchFragYesNoAllMatchingStone.deselect();
//        mBinding.searchFragYesNoAllListed.deselect();
//        mBinding.searchFragYesNoAllReserved.deselect();
//        mBinding.searchFragYesNoAllOnHold.deselect();
//        mBinding.searchFragYesNoAllNewArrival.deselect();
//        mBinding.searchFragTrayText.setText("Seatch Tray...");
//        mBinding.searchFragTrayText.setTextColor(getResources().getColor(R.color.grey));
//        PagedQueryManager.getInstance().updateQueryObject("Tray",null);
//        mBinding.searchFragMemoText.setText("Memo Destinations...");
//        mBinding.searchFragMemoText.setTextColor(getResources().getColor(R.color.grey));
//        PagedQueryManager.getInstance().updateQueryObject("MemoDestination",null);
//
//        PagedQueryManager.getInstance().resetFreeText();
//        mBinding.searchFragInputView.setText("");
//
//        search(null);
//    }
//
//    public void resetAllSearchData(){
//        PagedQueryManager.getInstance().restoreSortingToDefault();
//        PagedQueryManager.getInstance().setOnBehalfOfMember(null);
//        mBinding.searchFragOnBehalfMember.setText("");
//        mBinding.searchFragOnBehalfMemberCancel.setVisibility(GONE);
//        mBinding.searchFragInputCancelBtn.setImageResource(R.drawable.btn_qr);
//        resetSearchParam();
//        PagedQueryManager.getInstance().clearAll();
//        initTopRightIcon();
//    }
//
//    public void qrSearch(final String qrStr) {
//        if(TextUtils.isEmpty(qrStr))
//            return;
//        ((MainActivity)getActivity()).handlePb(true);
//        PagedQueryManager.getInstance().setFreeText(qrStr);
//
//        final Handler handler = new Handler();
//        new ApiSearch(getActivity()).getNext(0, new TAction<SearchObject>() {
//            @Override
//            public void execute(final SearchObject searchObject) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("wow","success!!!");
//                        ((MainActivity)getActivity()).handlePb(false);
//                        ResultsActivity.startWithSearchObj(getActivity(), searchObject);
//                    }
//                });
//            }
//        }, new TAction<String>() {
//            @Override
//            public void execute(final String s) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ((MainActivity)getActivity()).handlePb(false);
//                        Log.d("wow","booooooo!!! " + s);
//                    }
//                });
//            }
//        });
//
//        mBinding.searchFragInputView.post(new Runnable() {
//            @Override
//            public void run() {
//                mBinding.searchFragInputView.setText(qrStr);
//            }
//        });
//    }
//
//
//}
