package Service;

import Domain.Order;
import Pricing.PricingStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        for (var item : order.getItems()) {
            BigDecimal ogPrice = item.getPrice();
            if (item.getAddOns().size() >= 3) {
                BigDecimal discount = ogPrice.multiply(new BigDecimal("0.90"))
                        .setScale(2, RoundingMode.HALF_UP);
                System.out.println(item.getBase().getName() + " - " + discount + " Discounted");
            }
            else {
                System.out.println(item.getBase().getName() + " - " + ogPrice);
            }
        }
        System.out.println("___________________");
        System.out.println("TOTAL IS " + total);
    }
}
