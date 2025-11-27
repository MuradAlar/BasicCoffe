package Pricing;

import Domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MaxAddonsDiscount implements PricingStrategy{

    @Override
    public BigDecimal apply(Order order, BigDecimal subtotal) {
        BigDecimal total =  BigDecimal.ZERO;
        for (var beverage : order.getItems()) {
            var price = beverage.getPrice();
            if (beverage.getAddOns().size() >= 3) {
                total = total.add(price.multiply(new BigDecimal("0.9")));
                total = total.setScale(2, RoundingMode.HALF_UP);
            }
            else {
                total = total.add(price);
            }
        }
        return total;
    }
}
