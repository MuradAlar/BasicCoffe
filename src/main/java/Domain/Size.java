package Domain;

import java.math.BigDecimal;

public enum Size {
    SMALL(BigDecimal.ZERO), MEDIUM(new BigDecimal("0.50")), LARGE(new BigDecimal("1.00"));

    private final BigDecimal extra;

    Size(BigDecimal extra) {
        this.extra = extra;
    }

    public BigDecimal getExtra() {
        return extra;
    }
}
