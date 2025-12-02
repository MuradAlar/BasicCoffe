package Service;

import Domain.Order;
import Pricing.PricingStrategy;

import java.math.BigDecimal;

public class OrderService {

    private final PricingStrategy pricingStrategy;

    public OrderService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal subtotal = order.calculateSubtotal();
        return pricingStrategy.apply(order, subtotal);
    }

    public void printReceipt(Order order, BigDecimal total) {
        System.out.println("----RECEIPT----");

        order.getItems().forEach(item -> {
            System.out.println(item.getBase().getName() + " = " + item.getPrice());
        });

        System.out.println("___________________");
        System.out.println("TOTAL IS " + total);
    }
}
