package domain;

import java.math.BigDecimal;

public enum Size {
    SMALL, MEDIUM, LARGE;
    public BigDecimal getPrice(BigDecimal basePrice) {
        return switch (this) {
            case SMALL -> basePrice;
            case MEDIUM -> basePrice.multiply(new BigDecimal("1.3"));
            case LARGE -> basePrice.multiply(new BigDecimal("1.5"));
        };
    }
}
