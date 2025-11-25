package Pricing;

import Domain.Order;

import java.math.BigDecimal;

public class MaxAddonsDiscount implements PricingStrategy{

    @Override
    public BigDecimal apply(Order order, BigDecimal subtotal) {
        BigDecimal total = BigDecimal.ZERO;
        for (var beverage : order.getItems()) {
            var price = beverage.getPrice();
            if (beverage.getAddOns().size() >= 3) {
                return price.multiply(new BigDecimal("0.9"));
            }
            total = total.add(price);
        }
        return subtotal;
    }
}
