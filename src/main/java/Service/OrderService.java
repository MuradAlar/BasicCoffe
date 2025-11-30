package Service;
import Domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderService {
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        for (var drink : order.getItems()) {
            BigDecimal price = drink.getPrice();
            if (drink.getAddOns().size() >= 3) {
                price = price.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
            }
            total = total.add(price);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
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
