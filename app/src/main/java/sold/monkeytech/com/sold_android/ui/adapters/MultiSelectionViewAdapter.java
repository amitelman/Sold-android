/*
package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mid.mid.R;
import com.mid.mid.framework.Utils.KeyboardAndTextUtils;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

*/
/**
 * Created by MonkeyFather on 11/07/2017.
 *//*


public class MultiSelectionViewAdapter extends RecyclerView.Adapter<MultiSelectionViewAdapter.MyViewHolder> {

    private Context context;
    private boolean shouldUseSquareUi;
    private TAction<Boolean> cancelBtnAction;
    private List<String> optionsList;
    HashMap<Integer, String> selectedViews = new HashMap<Integer, String>();
    private Action onDiffClickAction;
    private Boolean isMultiSelect = false;
    private MultiSelectionViewAdapterListener listener;
    private List<String> defaultItems = new ArrayList<>();

    public MultiSelectionViewAdapter(Context context, List<String> optionsList, boolean shouldUseSquareUi, TAction<Boolean> cancelBtnAction, Boolean isMultiSelect) {
        this.optionsList = optionsList;
        this.shouldUseSquareUi = shouldUseSquareUi;
        this.context = context;
        this.cancelBtnAction = cancelBtnAction;
        if(isMultiSelect != null)
            this.isMultiSelect = isMultiSelect;
    }

    public MultiSelectionViewAdapter(Context context, List<String> optionsList, boolean shouldUseSquareUi, TAction<Boolean> cancelBtnAction) {
        new MultiSelectionViewAdapter(context, optionsList, shouldUseSquareUi, cancelBtnAction, null);
//        this.optionsList = optionsList;
//        this.shouldUseSquareUi = shouldUseSquareUi;
//        this.context = context;
//        this.cancelBtnAction = cancelBtnAction;
    }

    public void setListener(MultiSelectionViewAdapterListener listener) {
        this.listener = listener;
    }

    public void setDefualtItems(List<String> defaultItems) {
        this.defaultItems = defaultItems;

        for(String defaultItem : defaultItems){
            for(int i=0; i < optionsList.size(); i++){
                if(optionsList.get(i).equals(defaultItem))
                    selectedViews.put(i, defaultItem);
            }
        }
        handlerCancelBtn(true);
        notifyDataSetChanged();
    }

    public interface MultiSelectionViewAdapterListener {
        void onOptionClick(String s);
    }


    @Override
    public int getItemCount() {
        if(optionsList != null)
            return optionsList.size();
        return 0;
    }

    public void updateOptions(List<String> optionsStr){
        if(optionsList != null){
            this.optionsList.clear();
        }else{
            this.optionsList = new ArrayList<>();
        }
        if(optionsStr != null && optionsStr.size() > 0)
            this.optionsList.addAll(optionsStr);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("MultiOptionsViewAdapter", "LayoutInflater");
        View itemView;
        if (shouldUseSquareUi) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_selection_view_item_square, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_selection_view_item, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView option;
        public LinearLayout layout;

        public MyViewHolder(final View view) {
            super(view);
            option = (TextView) view.findViewById(R.id.multiOptionViewItemName);
            layout = (LinearLayout) view.findViewById(R.id.multiOptionViewItemLayout);
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.option.setText(optionsList.get(position));
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View optionView) {
                if(isMultiSelect){
                    if (selectedViews.containsKey(position)) { // if this option is already selected
                        selectedViews.remove(position);
                        notifyDataSetChanged();
                        handlerCancelBtn(false);
                    } else { // if this option isnt selected yet
                        selectedViews.put(position, ((TextView) optionView).getText().toString());
                        notifyDataSetChanged();
                        handlerCancelBtn(true);
                    }
                }
                else{
                    selectedViews.clear();
                    selectedViews.put(position, ((TextView) optionView).getText().toString());
                    notifyDataSetChanged();
                    handlerCancelBtn(true);
                }
                if(listener != null){
                    if(getSelected() != null)
                        listener.onOptionClick(getSelected());
                }
            }
        });

        if (selectedViews.get(position) != null) {
            KeyboardAndTextUtils.getSelectedUiTextView(holder.option);
        } else {
            KeyboardAndTextUtils.getUnSelectedUiTextView(holder.option);
        }

    }

    private void handlerCancelBtn(boolean b) {
        if(!b && selectedViews.size() > 0)
            return;
        if(onDiffClickAction != null){
            onDiffClickAction.execute();
        }else if(cancelBtnAction != null){
            cancelBtnAction.execute(b);
        }
    }

    public String getSelected() {
        String selectedCsv = "";
        if (getIsSelected()) {
            for (String key : selectedViews.values()) {
                selectedCsv += key + ",";
            }
            return selectedCsv.substring(0, selectedCsv.length()-1);
        }
        return null;
    }

    public boolean getIsSelected() {
        return selectedViews.size() > 0;
    }

    public void deselect() {
        if (getIsSelected()) {
            selectedViews.clear();
            notifyDataSetChanged();
        }
    }

    public void setOnDiffClickAction(Action onDiffClickAction){
        this.onDiffClickAction = onDiffClickAction;
    }

    public static void setMarginZero (View v) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        v.setLayoutParams(params);
    }
}

*/
