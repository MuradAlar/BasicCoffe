package Domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<PreparedBeverage> items = new ArrayList<>();

    public void addItem(PreparedBeverage preparedBeverage) {
        items.add(preparedBeverage); //ready drink from main. choice == 1
    }

    public List<PreparedBeverage> getItems() {
        return items;
    }
    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (var drink : items) {
            subtotal = subtotal.add(drink.getPrice());
        }

        return subtotal;
    }
    public void clear() {
        items.clear();
    }
}
