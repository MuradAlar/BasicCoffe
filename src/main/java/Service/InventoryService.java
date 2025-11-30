package Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();
    private static final String CSV_PATH = "src/main/resources/menu.csv";
    private static final Map<Integer, String> coffees = Map.of(
            1,"Espresso",
            2,"Latte",
            3,"Tea",
            4,"Americano",
            5,"Iced tea",
            6,"Cappuccino");

    public InventoryService() { // Load from CSV
        File file = new File(CSV_PATH);
        if (!file.exists()) {
            stock.put("Espresso", 10);
            stock.put("Latte", 1);
            stock.put("Tea", 5);
            stock.put("Americano", 4);
            stock.put("Iced Tea", 10);
            stock.put("Cappuccino", 10);
            saveStockToCsv();
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 2) continue;
                String drink = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                stock.put(drink, quantity);
            }
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Loading error happened ");
            stock.put("Espresso", 10);
            stock.put("Latte", 1);
            stock.put("Tea", 5);
            stock.put("Americano", 4);
            stock.put("Iced Tea", 10);
            stock.put("Cappuccino", 10);
            saveStockToCsv();
        }
    }
    public void saveStockToCsv() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH))) {
            writer.write("Drink,Stock\n");
            for(Map.Entry<String, Integer> entry : stock.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error saving to CSV");
        }
    }

    private int validInt(Scanner scanner) {
        while (true) {
            System.out.print("Choose: ");
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public void restockMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. View stock available");
            System.out.println("2. Restock item");
            System.out.println("3. Exit to main");
            int input = validInt(scanner);

            switch (input) {
                case 1 -> displayStock();
                case 2 -> handleRestock(scanner);
                case 3 -> {
                    System.out.println("Back to main menu.");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void displayStock() {
        System.out.println("\n--- Current Inventory Levels ---");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.printf("%-15s: %d units%n", entry.getKey(), entry.getValue());
        }
    }

    public void decreaseStock(String item) {
        int current = stock.getOrDefault(item, 0);
        if (current <= 0) return;
        stock.put(item, current - 1);
        saveStockToCsv();
    }

    public boolean isStock(String item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void handleRestock(Scanner scanner) {
        System.out.println("\nAvailable items: \n");
        for (Map.Entry<Integer, String> entry : coffees.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        while (true) {
            System.out.print("\nEnter item name to restock (or 'exit' to go back): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return;
            }

            if (input.isEmpty()) {
                System.out.println("Please type something!");
                continue;
            }

            int userChoice;

            try {
                userChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            if (userChoice < 1 || userChoice > 6) {
                System.out.println("Please enter a valid number of coffee between 1 and 6");
                continue;
            }

            String foundItem = coffees.get(userChoice);
            System.out.println("You chose: " + foundItem);



            if (foundItem == null) {
                System.out.println("Item '" + input + "' not found. Try typing part of the name (e.g., 'lat' for Latte)");
                continue;
            }

            int quantity = 0;
            while (quantity <= 0) {
                System.out.print("How many " + foundItem + " to add? ");
                String qtyInput = scanner.nextLine().trim();

                if (qtyInput.equalsIgnoreCase("exit")) return;

                try {
                    quantity = Integer.parseInt(qtyInput);
                    if (quantity <= 0) {
                        System.out.println("Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("That's not a valid number. Try again.");
                }
            }

            updateStock(foundItem, quantity);
            System.out.println("✓ Added " + quantity + " " + foundItem + "(s). Current: " + stock.get(foundItem));
            saveStockToCsv();
        }
    }

    public void updateStock(String item, int quantity) {
        int current = stock.getOrDefault(item, 0);
        stock.put(item, current + quantity);
    }
}
