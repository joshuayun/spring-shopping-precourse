package shopping.product;

import jakarta.validation.constraints.NotNull;

public record Product (
        Long id,

       //@Size(min=1, max=15)
        //@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9\\(\\)\\[\\]\\+\\-\\&\\/\\_\\s]*$", message = "Invalid characters found. Only ( ), [ ], +, -, &, /, _ are allowed.")
        String name,

        @NotNull
        int price,
        @NotNull
        String imgeUrl
) {
}
