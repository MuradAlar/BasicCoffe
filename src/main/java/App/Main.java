package App;
import Domain.*;
import Pricing.MaxAddonsDiscount;
import Service.InventoryService;
import Service.OrderService;
import Service.ServiceHelper;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static OrderService orderService = new OrderService(new MaxAddonsDiscount());
    static Order order = new Order();
    static InventoryService inventoryService = new InventoryService();

    private static final String PASSWORD = "StarSucks!";

    public static void main(String[] args) {

        while (true) {
            System.out.println("|||||Coffee Shop|||||");
            System.out.println("1. Add Beverage");
            System.out.println("2. View Chart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.println("5. Login (Staff member only)");
            System.out.println("Choose: ");

            int choice = readChoice(scanner, 5);

            if (choice == 1) {
                var preparedBeverage = createBeverage(scanner);
                if (preparedBeverage != null) {
                    order.addItem(preparedBeverage);
                    System.out.println("Drink added to chart!");
                }
            }
            else if (choice == 2) {
                printCart(order);
            }
            else if (choice == 3) {
                var total = orderService.calculateTotal(order);
                orderService.printReceipt(order, total);
                order.clear();
            }
            else if (choice == 4) {
                System.out.println("See ya");
                break;
            }
            else if (choice == 5) {
                System.out.println("Enter password");
                String password = scanner.nextLine();
                if (password.equals(PASSWORD)) {
                    System.out.println("ACCESS GRANTED");
                    inventoryService.restockMenu(scanner);
                }
                else {
                    System.out.println("Wrong password");
                }
            }
        }
    }

    private static Beverage createBeverage(Scanner scanner) {
        List<Beverage> beverages = List.of(
                new Espresso(),
                new Latte(),
                new Tea(),
                new Americano(),
                new IcedTea(),
                new Cappuccino()
        );
        //  MENU PRINTS
        System.out.println("Choose your beverage:");
        for (int i = 0; i < beverages.size(); i++) {
            Beverage bev = beverages.get(i);
            System.out.println((i + 1) + ". " + bev.getName() + " (" + bev.getBasePrice() + ")");
        }
        System.out.println("Choose: ");

        int drinkChoice = readChoice(scanner, beverages.size());

        if (drinkChoice < 1 || drinkChoice > beverages.size()) {
            System.out.println("Invalid. Defaulting to Tea");
            drinkChoice = 3; // Tea default
        }
        // index starts at 0
        Beverage base = beverages.get(drinkChoice - 1);


        if (!inventoryService.isStock(base.getName())) {
            System.out.println("Sorry " + base.getName() + " is out of stock at the time");
            return null;
        }
        else {
            System.out.println("you chose " + base.getName());

        }
        inventoryService.decreaseStock(base.getName());
        System.out.println("------------------");
        System.out.println("Chose size: ");
        System.out.println("1. Small ");
        System.out.println("2. Medium (+30%)");
        System.out.println("3. Large (+50%)");

        int sizeChoice = readChoice(scanner, 3);

        if (sizeChoice < 1 || sizeChoice > 3) {
            System.out.println("Invalid size. Default SMALL.");
        }
        Size size = switch (sizeChoice) {
            case 2 -> Size.MEDIUM;
            case 3 -> Size.LARGE;
            default -> Size.SMALL;
        };
        base.setSize(size);

        int maxAddon = 0;
        while (maxAddon < 3) {
            System.out.println("++++ADDONS++++");
            System.out.println("1. Chocolate drops  (+1.00)");
            System.out.println("2. Oat Milk (+0.80)");
            System.out.println("3. Syrup (+0.40) ");
            System.out.println("4. Cream (+0.50) ");
            System.out.println("5. Done");
            System.out.println("Choose (3 attempts are allowed):");
            int addChoice = readChoice(scanner, 5);

            switch (addChoice) {
                case 1 -> base.addAddOn(AddOn.CHOCOLATE_DROPS);
                case 2 -> base.addAddOn(AddOn.OAT_MILK);
                case 3 -> base.addAddOn(AddOn.SYRUP);
                case 4 -> base.addAddOn(AddOn.CREAM);
                case 5 -> System.out.println("Addons are done");
                default -> System.out.println("invalid");
            }
            if (addChoice == 5) {
                break;
            }
            maxAddon++;
        }
        return base;
    }

    private static void printCart(Order order) {
        System.out.println("///// Cart");
        if (order.getItems().isEmpty()) {
            System.out.println("Your chart is empty");
        }
       ServiceHelper.printReceipt(order);
    }

    // helper
    private static int readChoice(Scanner scanner, int max) {
        while (true) {
            try {
                int c = Integer.parseInt(scanner.nextLine());
                if (c >= 1 && c <= max) return c;
            } catch (Exception ignore) {}
            System.out.println("Invalid input. Try again:");
        }
    }
}

