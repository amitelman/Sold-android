package sold.monkeytech.com.sold_android.framework.models;


import sold.monkeytech.com.sold_android.framework.cache.IdLabelCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 18/10/2017.
 */

public class TaxBracket extends BaseModel<TaxBracket> {

    private int propertiesCount;
    private String taxRate;
    private Price minPrice;
    private Price maxPrice;

    @Override
    public BaseCache getInstanceOfCache() {
        return null;
    }

    @Override
    protected Class getType() {
        return TaxBracket.class;
    }

    public int getPropertiesCount() {
        return propertiesCount;
    }

    public void setPropertiesCount(int propertiesCount) {
        this.propertiesCount = propertiesCount;
    }

    public String getTaxRateString() {
        return taxRate;
    }

    public Double getTaxRate() {
        return Double.parseDouble(getTaxRateString());
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public Price getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Price minPrice) {
        this.minPrice = minPrice;
    }

    public Price getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Price maxPrice) {
        this.maxPrice = maxPrice;
    }
}
