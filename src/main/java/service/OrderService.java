package service;

import domain.Order;
import pricing.PricingStrategy;
import java.math.BigDecimal;


public class OrderService {


    private final PricingStrategy pricingStrategy;

    public OrderService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal subtotal = BigDecimal.ZERO;
        return pricingStrategy.apply(order, subtotal);
    }

    public void printReceipt(Order order, BigDecimal total) {
        System.out.println("----RECEIPT----");
        ServiceHelper.printReceipt(order);
        System.out.println("___________________");
        System.out.println("TOTAL IS " + total);
    }
}

