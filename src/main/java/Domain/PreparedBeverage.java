package Domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Getter
public class PreparedBeverage {

    private final Beverage base;
    private final Size size;
    private final List<AddOn> addOns = new ArrayList<>();

    public void addAddOn(AddOn addOn) {
        if (this.base instanceof Tea || this.base instanceof IcedTea) {
            String addOnName = addOn.getName().toLowerCase();
            if (addOnName.contains("chocolate drops")
                    || addOnName.contains("oat milk")
                    || addOnName.contains("cream")) {
                System.out.println("You cannot chose this addOn on Tea/IcedTea");
                return;
            }
        }
        addOns.add(addOn);
    }

    public BigDecimal getPrice() {
        var total = size.getPrice(base.getPrice());
        for (var addOn : addOns) {
            total = total.add(addOn.getPrice());
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
