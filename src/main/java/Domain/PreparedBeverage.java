package Domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PreparedBeverage {
    private final Beverage base;
    private final Size size;
    private final List<AddOn> addOns = new ArrayList<>();

    public PreparedBeverage(Beverage base, Size size) {
        this.base = base;
        this.size = size;
    }

    public void addAddOn(AddOn addOn) {
        if (this.base instanceof Tea || this.base instanceof IcedTea) {
            String addOnName = addOn.getName().toLowerCase();
            if (addOnName.contains("chocolate drops") || addOnName.contains("oat milk")
            || addOnName.contains("cream")) {
                System.out.println("You cannot chose this addOn on Tea/IcedTea");
                return;
            }
        }
        addOns.add(addOn);
    }

    public Size getSize() {
        return  size;
    }
    public Beverage getBase() {
        return base;
    }
    public List<AddOn> getAddOns() {
        return addOns;
    }
    public BigDecimal getPrice() {
        var total = base.getPrice().add(size.getExtra());
        for (var addOn : addOns) {
            total = total.add(addOn.getPrice());
        }
        return total;
    }
}
