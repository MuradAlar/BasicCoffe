package Domain;

import java.math.BigDecimal;

public class IcedTea extends Beverage{
    @Override
    public String getName() {
        return "IcedTea";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.80");
    }
}
