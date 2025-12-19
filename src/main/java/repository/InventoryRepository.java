package repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryRepository {
    private static final String CSV_PATH = "src/main/resources/menu.csv";

    public Map<String, Integer> loadStock() {
        Map<String, Integer> stock = new HashMap<String, Integer>();
        File file = new File(CSV_PATH);

        if(!file.exists()) return createDefaultStock();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    stock.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                }
            }
        } catch (IOException e) {
            return createDefaultStock();
        }
        return stock;
    }

    public void saveStock(Map<String, Integer> stock) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH))) {
            writer.write("Drink,Stock\n");
            for (Map.Entry<String, Integer> entry : stock.entrySet()) {
                writer.write(entry.getKey() + ", " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error. Couldn't save inventory");
        }
    }

    public Map<String, Integer> createDefaultStock() {
        return new HashMap<>(Map.of("Espresso", 10, "Latte", 10, "Tea", 10,
                "Americano", 10, "IcedTea", 10, "Cappuccino", 10));
    }
}
