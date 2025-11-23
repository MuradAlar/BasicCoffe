package Service;

import Domain.Order;
import Domain.PreparedBeverage;
import Pricing.PricingStrategy;

import java.math.BigDecimal;

public class OrderService {
    public BigDecimal calculateSubtotal(Order order) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for(PreparedBeverage beverage : order.getItems()) {
            subtotal = subtotal.add(beverage.getPrice());
        }
        return subtotal;
    }
    public BigDecimal calculateDiscount(Order order, PricingStrategy strategy){
        BigDecimal subtotal = calculateSubtotal(order);
        return strategy.apply(order, subtotal);
    }
    public void printReceipt(Order order, BigDecimal total) {
        System.out.println("----RECEIPT----");
        for (PreparedBeverage beverage : order.getItems()) {
            System.out.println(beverage.getBase().getName() + "-" + beverage.getPrice());
        }
        System.out.println("TOTAL IS " + total);
    }
}
