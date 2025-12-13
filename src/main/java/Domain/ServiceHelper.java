package Domain;

import java.math.BigDecimal;



public class ServiceHelper {
    public static void printReceipt(Order order) {
        for (PreparedBeverage drink : order.getItems()) {
            BigDecimal price = drink.getPrice();
            if (drink.getAddOns().size() >= 3) {
                BigDecimal discountPrice = price.multiply(new BigDecimal("0.9"))
                        .setScale(2, java.math.RoundingMode.HALF_UP);
                System.out.println(drink.getBase().getName() + " " + discountPrice + " (Discounted)");
            }
            else {
                System.out.println(drink.getBase().getName() +  " = " + price);
            }
        }
    }
}
