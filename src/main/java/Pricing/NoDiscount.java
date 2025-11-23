package Pricing;
import Domain.Order;
import java.math.BigDecimal;

public class NoDiscount implements PricingStrategy{
    @Override
    public BigDecimal apply(Order order, BigDecimal subtotal) {
        return subtotal;
    }
}
