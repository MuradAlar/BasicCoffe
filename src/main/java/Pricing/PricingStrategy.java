package Pricing;

import Domain.Order;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal apply(Order order, BigDecimal subtotal);
}
