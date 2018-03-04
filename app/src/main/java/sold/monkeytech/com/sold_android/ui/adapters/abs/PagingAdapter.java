package sold.monkeytech.com.sold_android.ui.adapters.abs;

import android.widget.BaseAdapter;

/**
 * Created by elitu on 6/28/15.
 */
public abstract class PagingAdapter<T>  extends BaseAdapter {
    public abstract void addMoreItems(T items);


}
