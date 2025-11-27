package App;
import Domain.*;
import Service.InventoryService;
import Service.OrderService;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static OrderService orderService = new OrderService();
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
                var PreparedBeverage = createBeverage(scanner);
                order.addItem(PreparedBeverage);
                System.out.println("Drink added");
            }
            else if (choice == 2) {
                printCart(order);
            }
            else if (choice == 3) {
                var total = orderService.calculateDiscount(order);
                orderService.printReceipt(order, total);
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

    private static PreparedBeverage createBeverage(Scanner scanner) {
        System.out.println("\n Choose a drink: ");
        System.out.println("1. Espresso (2.00)");
        System.out.println("2. Latte (2.50)");
        System.out.println("3. Tea (1.20)");
        System.out.println("4. Americano (2.50)");
        System.out.println("5. Iced Tea (1.80)");
        System.out.println("6. Cappuccino (2.40)");
        System.out.println("Choose: ");

        int drinkChoice = readChoice(scanner, 6);

        Beverage base = switch (drinkChoice) {
            case 1 -> new Espresso();
            case 2 -> new Latte();
            case 3 -> new Tea();
            case 4 -> new Americano();
            case 5 -> new IcedTea();
            case 6 -> new Cappuccino();
            default -> {
                System.out.println("Invalid. Default Tea");
                yield new Tea();
            }
        };
        System.out.println("you chose " + base.getName());
        System.out.println("------------------");
        System.out.println("Chose size: ");
        System.out.println("1. Small ");
        System.out.println("2. Medium (+0.50)");
        System.out.println("3. Large (+1.00)");

        int sizeChoice = readChoice(scanner, 3);

        Size size = switch (sizeChoice) {
        case 1 -> Size.SMALL;
        case 2 -> Size.MEDIUM;
        case 3 -> Size.LARGE;
            default -> {
                System.out.println("Invalid size. Default SMALL.");
                yield Size.SMALL;
            }
        };

        var prepared = new PreparedBeverage(base, size);

        int i = 0;
        while (i < 3) {
            System.out.println("++++ADDONS++++");
            System.out.println("1. Chocolate drops  (+1.00)");
            System.out.println("2. Oat Milk (+0.80)");
            System.out.println("3. Syrup (+0.40) ");
            System.out.println("4. Cream (+0.50) ");
            System.out.println("5. Done");
            System.out.println("Choose (3 attempts are allowed):");
            int addChoice = readChoice(scanner, 5);

            if (addChoice == 1) {
                prepared.addAddOn(new AddOn("Chocolate drops", new BigDecimal("1.00")));
            }
            else if (addChoice == 2) {
                prepared.addAddOn(new AddOn("Oat Milk", new BigDecimal("0.80")));
            }
            else if (addChoice == 3) {
                prepared.addAddOn(new AddOn("Syrup", new BigDecimal("0.40")));
            }
            else if (addChoice == 4) {
                prepared.addAddOn(new AddOn("Cream", new BigDecimal("0.50")));
            }
            else if (addChoice == 5) {
                break;
            }
            i++;
        }
        return prepared;
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

    private static void printCart(Order order) {
        System.out.println("///// Chart");
        if (order.getItems().isEmpty()) {
            System.out.println("Your chart is empty");
        }
        for (PreparedBeverage drink : order.getItems()) {
            BigDecimal price = drink.getPrice();
            if (drink.getAddOns().size() >= 3) {
                BigDecimal discountPrice = price.multiply(new BigDecimal("0.9"));
                discountPrice = discountPrice.setScale(2, java.math.RoundingMode.HALF_UP);
                System.out.println(drink.getBase().getName() + " " + discountPrice + " (Discounted)");
            }
            else {
            System.out.println(drink.getBase().getName() +  " = " + price);
            }
        }
    }
}

