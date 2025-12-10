package Domain;
import java.math.BigDecimal;
public abstract class Beverage {
    public abstract String getName();
    public abstract BigDecimal getPrice();

    public boolean isAddOnAllowed(AddOn addOn) {
        return true;
        // default
    }
}
