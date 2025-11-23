package Domain;

import java.math.BigDecimal;

public class Latte extends Beverage{
    @Override
    public String getName() {
        return "Latte";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.50");
    }
}
