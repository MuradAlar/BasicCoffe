package Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();
    public void inventory(Scanner scanner) {
        stock.put("Espresso", 10);
        stock.put("Latte", 10);
        stock.put("Tea", 5);
        stock.put("Americano", 4);
        stock.put("Iced Tea", 10);
        stock.put("Cappuccino", 10);
    }

    public void restockMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. View stock available");
            System.out.println("2. Restock item");
            System.out.println("3. Exit to main");
            int input = scanner.nextInt();

            switch (input) {
                case 1 : displayStock();
                break;
                case 2 : restock(scanner);
                break;
                case 3 :
                    System.out.println("Back to main");
                    return;
                default:
                    System.out.println("Invalid option. Try Again");
            }

        }
    }
    public void restock(Scanner scanner) {
        System.out.println("Restock menu. Enter item to restock: ");
        String item = scanner.nextLine().toLowerCase();
        System.out.println("Enter quantity of this item");
        int quantity = scanner.nextInt();
        try {
            if (quantity <= 0) {
                System.out.println("Enter valid number");
                return;
            }
            updateStock(item, quantity);
            System.out.println("Item added");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid quantity");
        }
    }

    public void updateStock(String item, int quantity) {
        int currentStock = stock.getOrDefault(item, 0);
        stock.put(item, quantity + currentStock);
    }
    public void displayStock() {
        System.out.println("Welcome to inventory");
        System.out.println("\n--- Current Inventory Levels ---");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.printf("   %-15s: %d units%n", entry.getKey(), entry.getValue());
        }
    }
}
