package Domain;

import java.math.BigDecimal;

public class Tea extends Beverage{
    @Override
    public String getName() {
        return "Tea";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.25");
    }
}
