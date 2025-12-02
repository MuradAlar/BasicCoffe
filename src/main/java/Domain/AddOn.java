package Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AddOn {
    private final String name;
    private final BigDecimal price;

}
