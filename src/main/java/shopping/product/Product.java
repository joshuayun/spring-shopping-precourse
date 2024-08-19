package shopping.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record Product (
        Long id,
        @NotNull
        String name,
        @NotNull
        int price,
        @NotNull
        String imgeUrl
) {
}
