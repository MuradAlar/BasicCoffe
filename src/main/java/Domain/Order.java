package Domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private final List<Beverage> items = new ArrayList<>();

    public void addItem(Beverage item) {
        items.add(item); //ready drink from main. choice == 1
    }

    public void clear() {
        items.clear();
    }
}
