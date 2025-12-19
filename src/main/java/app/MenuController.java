package app;

import domain.Order;
import service.*;
import UI.ConsoleUI;
import pricing.MaxAddonsDiscount;

import java.util.Map;
import java.util.TreeMap;

public class MenuController {
    private final OrderService orderService = new OrderService(new MaxAddonsDiscount());
    private final repository.InventoryRepository repo = new repository.InventoryRepository();
    private final InventoryService inventoryService = new InventoryService(repo);
    private final BeverageBuilder beverageBuilder = new BeverageBuilder(inventoryService);
    private final Order currentOrder = new Order();
    private static final String PASSWORD = "StarSucks!";

    public void start() {
        while (true) {
            ConsoleUI.print("\n||||| Coffee Shop |||||");
            ConsoleUI.print("1. Add Beverage\n2. View Cart\n3. Checkout\n4. Exit\n5. Staff Login");

            int choice = ConsoleUI.readInt("Choose: ", 5);

            switch (choice) {
                case 1 -> {
                    var drink = beverageBuilder.buildBeverage();
                    if (drink != null) {
                        currentOrder.addItem(drink);
                        ConsoleUI.print("Added to cart!");
                    }
                }
                case 2 -> viewCart();
                case 3 -> checkout();
                case 4 -> { ConsoleUI.print("See ya!"); return; }
                case 5 -> handleLogin();
            }
        }
    }

    private void viewCart() {
        if (currentOrder.getItems().isEmpty()) {
            ConsoleUI.print("Your cart is empty.");
        } else {
            ServiceHelper.printReceipt(currentOrder);
        }
    }

    private void checkout() {
        var total = orderService.calculateTotal(currentOrder);
        orderService.printReceipt(currentOrder, total);
        currentOrder.clear();
    }

    private void handleLogin() {
        String pass = ConsoleUI.readString("Enter Password: ");
        if (pass.equals(PASSWORD)) {
            ConsoleUI.print("ACCESS GRANTED");
            restockMenu();
        } else {
            ConsoleUI.print("Wrong password!");
        }
    }
    private void restockMenu() {
        Map<Integer, String> coffees = new TreeMap<>(Map.of(
                1, "Espresso",
                2, "Latte",
                3, "Tea",
                4, "Americano",
                5, "IcedTea",
                6, "Cappuccino"
        ));
        while (true) {
            ConsoleUI.print("\n1. View stock \n2. Add item \n3. Exit");
            int choice = ConsoleUI.readInt("Choose:", 3);

            if(choice == 1) {
                inventoryService.getAllStock().forEach((item, quantity) ->
                        ConsoleUI.print(item + ":" + quantity));
            }
            else if (choice == 2) {
                ConsoleUI.print("\nSelect item to restock");
                coffees.forEach((key, name) -> ConsoleUI.print(key + ". " + name));
                int drinkChoice = ConsoleUI.readInt("Select (1-6): ", 6);
                String selectedName = coffees.get(drinkChoice);
                int quantity = ConsoleUI.readInt("How many?", 1000);
                inventoryService.addStock(selectedName, quantity);
                ConsoleUI.print("Added " + quantity + " to " + selectedName);
            }
            else {
                break;
            }

        }
    }

    public static void main(String[] args) {
        new MenuController().start();
    }
}