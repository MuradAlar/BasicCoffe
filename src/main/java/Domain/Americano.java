package Domain;

import java.math.BigDecimal;

public class Americano extends Beverage{
    @Override
    public String getName() {
        return "Americano";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.50");
    }
}
