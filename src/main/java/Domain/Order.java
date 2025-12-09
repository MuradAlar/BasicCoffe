package Domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private final List<PreparedBeverage> items = new ArrayList<>();

    public void addItem(PreparedBeverage preparedBeverage) {
        items.add(preparedBeverage); //ready drink from main. choice == 1
    }

    public void clear() {
        items.clear();
    }
}
