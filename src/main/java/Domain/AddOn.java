package Domain;

import java.math.BigDecimal;

public class AddOn {
    private final String name;
    private final BigDecimal price;

    public AddOn(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
