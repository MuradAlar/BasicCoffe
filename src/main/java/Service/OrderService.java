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
            BigDecimal price = beverage.getPrice();
            if (beverage.getAddOns().size() >= 3) {
                BigDecimal discountPrice = price.multiply(new BigDecimal("0.9"));
                discountPrice = discountPrice.setScale(2, java.math.RoundingMode.HALF_UP);
                System.out.println(beverage.getBase().getName()  + " "+ discountPrice + "(Discount applied)");
            }
           else {
                System.out.println(beverage.getBase().getName() + " = " + price);
            }
        }
        System.out.println("___________________");
        System.out.println("TOTAL IS " + total);
    }

}
