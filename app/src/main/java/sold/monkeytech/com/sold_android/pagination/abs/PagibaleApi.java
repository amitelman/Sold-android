package sold.monkeytech.com.sold_android.pagination.abs;

import com.monkeytechy.framework.interfaces.TAction;

/**
 * Created by elitu on 17/10/2017.
 */

public interface PagibaleApi<PageObject,ListType,ErrorType> {
    void getNext(PageObject pageData, TAction<ListType> onSuccess, TAction<ErrorType> onFail);
}
