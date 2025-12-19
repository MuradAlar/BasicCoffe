package domain;

import java.math.BigDecimal;

public class IcedTea extends Beverage{
    @Override
    public String getName() {
        return "IcedTea";
    }

    @Override
    public BigDecimal getBasePrice() {
        return new BigDecimal("1.80");
    }
@Override
    public boolean isAddOnAllowed(AddOn addOn) {
        return addOn == AddOn.SYRUP;
    }
}
