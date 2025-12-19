package domain;

import java.math.BigDecimal;

public class Cappuccino extends Beverage{
    @Override
    public String getName() {
        return "Cappuccino";
    }

    @Override
    public BigDecimal getBasePrice() {
        return new BigDecimal("2.40");
    }
}
