package Domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<PreparedBeverage> items = new ArrayList<>();

    public void addItem(PreparedBeverage drink) {
        items.add(drink);
    }

    public List<PreparedBeverage> getItems() {
        return items;
    }
}
