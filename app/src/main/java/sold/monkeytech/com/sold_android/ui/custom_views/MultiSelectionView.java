//package sold.monkeytech.com.sold_android.ui.custom_views;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.databinding.DataBindingUtil;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//
//import com.mid.mid.R;
//import com.mid.mid.databinding.MultiOptionsViewBinding;
//import com.mid.mid.framework.managers.DataFiltersManager;
//import com.mid.mid.ui.adapters.MultiSelectionViewAdapter;
//import com.mid.mid.ui.adapters.utils.SimpleDividerItemDecoration;
//import com.monkeytechy.framework.interfaces.TAction;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by MonkeyFather on 11/07/2017.
// */
//
//public class MultiSelectionView extends LinearLayout {
//
//    private MultiOptionsViewBinding mBinding;
//    private MultiSelectionViewAdapter adapter;
//    private boolean shouldUseSquareUi = false;
//    private boolean shouldShowCancelBtn;
//    private List<String> options;
//    private boolean isMultiSelect = false;
//
//    public MultiSelectionView(Context context) {
//        super(context);
//        initUi(context, null);
//    }
//
//    public MultiSelectionView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initUi(context,attrs);
//    }
//
//    public MultiSelectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initUi(context,attrs);
//    }
//
//    public MultiSelectionView setIsMultiSelect(boolean isMultiSelect){
//        this.isMultiSelect = isMultiSelect;
//        return this;
//    }
//
//    public void initUi(Context context, AttributeSet attrs) {
//        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.multi_options_view, this, true);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiOptionsViewValues);
//        //set title
//        String title = a.getString(R.styleable.MultiOptionsViewValues_title);
//        if(title != null){
//            mBinding.multiOptionsName.setText(title);
//        }
//        else{
//            mBinding.multiOptionsHeaderLayout.setVisibility(GONE);
//        }
//
//        shouldUseSquareUi = a.getBoolean(R.styleable.MultiOptionsViewValues_shouldSquare, false);
//        shouldShowCancelBtn = a.getBoolean(R.styleable.MultiOptionsViewValues_showCancelBtn, true);
//
//        isMultiSelect = shouldShowCancelBtn = a.getBoolean(R.styleable.MultiOptionsViewValues_isMultiSelect, true);
//        a.recycle();
//
//
//        TAction<Boolean> showCancelAction = new TAction<Boolean>() {
//            @Override
//            public void execute(Boolean shouldShow) {
//                if(shouldShow){
//                    showCancelBtn();
//
//                }else{
//                    hideCancelBtn();
//                }
//            }
//        };
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mBinding.MultiOptionsViewRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mBinding.MultiOptionsViewRecyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new MultiSelectionViewAdapter(getContext(), getOptions(), shouldUseSquareUi, showCancelAction, isMultiSelect);
//        mBinding.MultiOptionsViewRecyclerView.setAdapter(adapter);
//
//        initScrollBar();
//
//        mBinding.multiOptionsCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.deselect();
//                hideCancelBtn();
//            }
//        });
//
//        SimpleDividerItemDecoration decoration = new SimpleDividerItemDecoration(getContext());
//        mBinding.MultiOptionsViewRecyclerView.addItemDecoration(decoration);
//    }
//
//    private void initScrollBar() {
////        Log.d("wow",adapter.getItemCount() + " count of item");
//        if(adapter.getItemCount() <= 3){
//            mBinding.MultiOptionsViewRecyclerView.setHorizontalScrollBarEnabled(false);
//            LayoutParams params = (LayoutParams)mBinding.MultiOptionsViewRecyclerView.getLayoutParams();
//            params.setMargins(0, 0, 0, 0); //left, top, right, bottom
//            mBinding.MultiOptionsViewRecyclerView.setLayoutParams(params);
//        }else{
//            mBinding.MultiOptionsViewRecyclerView.setHorizontalScrollBarEnabled(true);
//            LayoutParams params = (LayoutParams)mBinding.MultiOptionsViewRecyclerView.getLayoutParams();
//            params.setMargins(0, 13, 0, 0); //left, top, right, bottom
//            mBinding.MultiOptionsViewRecyclerView.setLayoutParams(params);
//        }
//    }
//
//    private void showCancelBtn() {
//        mBinding.multiOptionsName.setTextColor(getResources().getColor(R.color.orange));
//        mBinding.multiOptionsCancel.setVisibility(VISIBLE);
//    }
//    private void hideCancelBtn() {
//        mBinding.multiOptionsName.setTextColor(getResources().getColor(R.color.black));
//        mBinding.multiOptionsCancel.setVisibility(GONE);
//    }
//
//    public String getSelectedOptions() {
//        return adapter.getSelected();
//    }
//
//    public String getSelectedLongStringOptions(){
//        return DataFiltersManager.getInstance().getCutSymPolValues(getSelectedOptions());
//    }
//
//    public boolean isSelected() {
//        return adapter.getIsSelected();
//    }
//
//    public void deselect(){
//        adapter.deselect();
//        hideCancelBtn();
//    }
//
//    public MultiSelectionViewAdapter getAdapter(){
//        if(adapter != null)
//            return adapter;
//        return null;
//    }
//
//    public List<String> getOptions() {
//        if(options == null)
//            return new ArrayList<>();
//        return options;
//    }
//
//    public void setOptions(List<String> options) {
////        this.options = options;
//        adapter.updateOptions(options);
//        initScrollBar();
//    }
//
//    public MultiSelectionView setListener(MultiSelectionViewAdapter.MultiSelectionViewAdapterListener listener) {
//        adapter.setListener(listener);
//        return this;
//    }
//
//    public void setDefaultItems(List<String> defaultItems){
//        adapter.setDefualtItems(defaultItems);
//    }
//
//}
