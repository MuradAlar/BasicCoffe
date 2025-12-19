package service;

import domain.*;
import UI.ConsoleUI;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BeverageBuilder {
    private final InventoryService inventoryService;

    public Beverage buildBeverage() {
        List<Beverage> menu = List.of(new Espresso(), new Latte(), new Tea(),
                new Americano(), new IcedTea(), new Cappuccino());

        ConsoleUI.print("--- Choose your beverage ---");          // choose Base
        for (int i = 0; i < menu.size(); i++) {
            ConsoleUI.print((i + 1) + ". " + menu.get(i).getName() + " ($" + menu.get(i).getBasePrice() + ")");
        }

        int choice = ConsoleUI.readInt("Choose: ", menu.size());
        Beverage base = menu.get(choice - 1);

        if (!inventoryService.isStock(base.getName())) {
            ConsoleUI.print("Sorry, " + base.getName() + " is out of stock!");
            return null;
        }

        inventoryService.decreaseStock(base.getName());

        // choose Size
        ConsoleUI.print("--- Choose Size --- \n1. Small\n2. Medium (+30%)\n3. Large (+50%)");
        int sizeChoice = ConsoleUI.readInt("Size: ", 3);
        base.setSize(switch(sizeChoice) {
            case 1 ->Size.SMALL;
            case 2 -> Size.MEDIUM;
            case 3 -> Size.LARGE;
            default -> Size.SMALL;
        });

        // Add-ons
        addAddons(base);
        return base;
    }

    private void addAddons(Beverage base) {
        for (int i = 0; i < 3; i++) {
            ConsoleUI.print("--- Add-ons (Max 3 allowed) ---");
            ConsoleUI.print("1. Chocolate (+1.00)\n2. Oat Milk (+0.80)\n3. Syrup (+0.40)\n4. Cream (+0.50)\n5. Done");
            int choice = ConsoleUI.readInt("Choice: ", 5);

            if (choice == 5) break;

            switch (choice) {
                case 1 -> base.addAddOn(AddOn.CHOCOLATE_DROPS);
                case 2 -> base.addAddOn(AddOn.OAT_MILK);
                case 3 -> base.addAddOn(AddOn.SYRUP);
                case 4 -> base.addAddOn(AddOn.CREAM);
            }
        }
    }
}