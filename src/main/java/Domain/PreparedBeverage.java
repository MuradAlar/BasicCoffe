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
        if (!base.isAddOnAllowed(addOn)) {
            System.out.println("This addOn is not compatible for this drink");
            return;
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
