package com.cavillon.market.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {

    private String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "sellPrice cannot be negative")
    private BigDecimal sellPrice;

    @DecimalMin(value = "0.0", inclusive = true, message = "buyPrice cannot be negative")
    private BigDecimal buyPrice;

    @Min(value = 0, message = "stock cannot be negative")
    private Integer stock;

}
