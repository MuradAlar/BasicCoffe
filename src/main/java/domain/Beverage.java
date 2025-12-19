package domain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public abstract class Beverage {
    private Size size = Size.SMALL;
    private final List<AddOn> addOns = new ArrayList<>();
    public abstract String getName();
    public abstract BigDecimal getBasePrice();

    public boolean isAddOnAllowed(AddOn addOn) {
        return true;
        // default
    }

    public void addAddOn(AddOn addOn) {
        if (!isAddOnAllowed(addOn)) {
            System.out.println("This addOn is not compatible for this drink");
            return;
        }
        addOns.add(addOn);
    }

    public BigDecimal getPrice() {
        var total = size.getPrice(getBasePrice());
        for (var addOn : addOns) {
            total = total.add(addOn.getPrice());
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
