package domain;

import java.math.BigDecimal;


public enum AddOn {
    CHOCOLATE_DROPS("Chocolate drops", new BigDecimal("1.00")),
    OAT_MILK("Oat milk", new BigDecimal("0.80")),
    SYRUP("Syrup", new BigDecimal("0.40")),
    CREAM("Cream", new BigDecimal("0.50"));

    private final String name;
    private final BigDecimal price;

    AddOn(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
