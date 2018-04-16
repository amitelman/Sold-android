package sold.monkeytech.com.sold_android.framework.serverapi.property;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiPreApproved extends AbstractServerApiConnector {

    public ApiPreApproved(Context context) {
        super(context);
    }

    public synchronized void request(final long propertyId, final int purchaseType, final int loanAmount, final int equity, final int employmentStatus, final String occupation,
                                     final int seniority, final int monthlyIncome, final int childCount, final int disposableIncome, final String otherIncome, final int fixesExpenses,
                                     final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                params.addInt("purchase_type", purchaseType)
                        .addInt("loan_amount", loanAmount)
                        .addInt("equity", equity)
                        .addInt("employment_status", employmentStatus)
                        .addText("occupation", occupation)
                        .addInt("seniority", seniority)
                        .addInt("monthly_income", monthlyIncome)
                        .addInt("child_count", childCount)
                        .addInt("disposable_income", disposableIncome)
                        .addText("other_incomes", otherIncome)
                        .addInt("fixed_expenses", fixesExpenses)
                        .addInt("property_id", (int) propertyId);
                RemoteResponseString remoteResponseString = performHTTPPost("/properties/" + propertyId + "/pre_approve", params);
                if (remoteResponseString.isSuccess()) {
                    if(onSuccess!=null)
                        onSuccess.execute();
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        }));
    }
}