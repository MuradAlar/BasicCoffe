package Domain;

import java.math.BigDecimal;

public class Espresso extends Beverage{
    @Override
    public String getName() {
        return "Espresso";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.00");
    }
}
