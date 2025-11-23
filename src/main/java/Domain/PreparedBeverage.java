package Domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PreparedBeverage {
    private final Beverage base;
    private final List<AddOn> addOns = new ArrayList<>();

    public PreparedBeverage(Beverage base) {
        this.base = base;
    }

    public void addAddOn(AddOn addOn) {
        addOns.add(addOn);
    }

    public Beverage getBase() {
        return base;
    }
    public List<AddOn> getAddOns() {
        return addOns;
    }
    public BigDecimal getPrice() {
        var total = base.getPrice();
        for (var addOn : addOns) {
            total = total.add(addOn.getPrice());
        }
        return total;
    }
}
