package Service;

import Domain.Order;
import Pricing.MaxAddonsDiscount;

import java.math.BigDecimal;

public class OrderService {
    public BigDecimal calculateSubtotal(Order order) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for(var beverage : order.getItems()) {
            subtotal = subtotal.add(beverage.getPrice());
        }
        return subtotal;
    }
    public BigDecimal calculateDiscount(Order order){
        var strategy = new MaxAddonsDiscount();
        BigDecimal subtotal = calculateSubtotal(order);
        return strategy.apply(order, subtotal);
    }
    public void printReceipt(Order order, BigDecimal total) {
        System.out.println("----RECEIPT----");
        for (var beverage : order.getItems()) {
            System.out.println(beverage.getBase().getName() + "-" + beverage.getPrice());
        }
        System.out.println("TOTAL IS " + total);
    }
}
