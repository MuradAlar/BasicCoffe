package service;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import repository.InventoryRepository;


@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    private final Map<String, Integer> stock;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
        this.stock = repository.loadStock();
    }

    public boolean isStock(String item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void decreaseStock(String item) {
        int current = stock.getOrDefault(item, 0);
        if (current > 0) {
            stock.put(item, current - 1);
            repository.saveStock(stock);
        }
    }

    public void addStock(String item, int quantity) {
        int current = stock.getOrDefault(item, 0);
        stock.put(item, current + quantity);
        repository.saveStock(stock);
    }

    public Map<String, Integer> getAllStock() {
        return Map.copyOf(stock);
    }
}