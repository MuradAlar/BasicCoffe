package Service;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("espresso_shot", 10);
        stock.put("milk", 13);
        stock.put("syrup", 15);
    }

    public boolean use(String item, int amount) {
        int current = stock.getOrDefault(item, 0);
        if(current < amount) {
            return false;
        }
        stock.put(item, current - amount);
        return true;
    }
    public Map<String, Integer> getStock() {
        return stock;
    }
}
