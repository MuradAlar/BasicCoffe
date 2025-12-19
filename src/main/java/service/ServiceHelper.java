package service;

import domain.Beverage;
import domain.Order;

import java.math.BigDecimal;



public class ServiceHelper {
    public static void printReceipt(Order order) {
        for (Beverage drink : order.getItems()) {
            BigDecimal price = drink.getPrice();
            if (drink.getAddOns().size() >= 3) {
                BigDecimal discountPrice = price.multiply(new BigDecimal("0.9"))
                        .setScale(2, java.math.RoundingMode.HALF_UP);
                System.out.println(drink.getName() + " = " + discountPrice + " (Discounted)");
            }
            else {
                System.out.println(drink.getName() +  " = " + price);
            }
        }
    }
}
