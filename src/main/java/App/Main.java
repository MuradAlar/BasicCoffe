package App;
import Domain.*;
import Pricing.NoDiscount;
import Service.OrderService;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var orderService = new OrderService();
        var strategy = new NoDiscount();
        var order = new Order();

        while (true) {
            System.out.println("|||||Coffee Shop|||||");
            System.out.println("1. Add Beverage");
            System.out.println("2. View Chart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.println("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                var drink = createBeverage(scanner);
                order.addItem(drink);
                System.out.println("Drink added");
            }
            else if (choice == 2) {
                printCart(order);
            }
            else if (choice == 3) {
                var total = orderService.calculateDiscount(order, strategy);
                orderService.printReceipt(order, total);
                break;
            }
            else if (choice == 4) {
                System.out.println("See ya");
                break;
            }
        }
    }

    private static PreparedBeverage createBeverage(Scanner scanner) {
        System.out.println("\n Choose a drink: ");
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.println("3. Tea");
        System.out.println("Choose: ");
        int drinkChoice = scanner.nextInt();
        scanner.nextLine();
        Beverage base;

        switch (drinkChoice) {
            case 1 -> base = new Espresso();
            case 2 -> base = new Latte();
            case 3 -> base = new Tea();
            default -> {
                System.out.println("Invalid. Default Tea");
                base = new Tea();
            }
        }

        PreparedBeverage prepared = new PreparedBeverage(base);

        while (true) {
            System.out.println("++++ADDONS++++");
            System.out.println("1. Extra shot (+1.00)");
            System.out.println("2. Oat Milk (+0.80)");
            System.out.println("3. Syrup (+0.40) ");
            System.out.println("4. Done");
            System.out.println("Choose ");
            int addChoice = scanner.nextInt();
            scanner.nextLine();

            if (addChoice == 1) {
                prepared.addAddOn(new AddOn("Extra shot", new BigDecimal("1.00")));
            }
            else if (addChoice == 2) {
                prepared.addAddOn(new AddOn("Oat Milk", new BigDecimal("0.80")));
            }
            else if (addChoice == 3) {
                prepared.addAddOn(new AddOn("Syrup", new BigDecimal("0.40")));
            }
            else if (addChoice == 4) {
                break;
            }
        }
        return prepared;
    }
    private static void printCart(Order order) {
        System.out.println("///// Chart");
        for (PreparedBeverage drink : order.getItems()) {
            System.out.println(drink.getBase().getName() + "---" + drink.getPrice());
        }
    }
}

