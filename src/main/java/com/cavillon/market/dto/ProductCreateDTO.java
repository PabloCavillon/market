package com.cavillon.market.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String description = "";

    private BigDecimal sellPrice = BigDecimal.ZERO;

    private BigDecimal buyPrice = BigDecimal.ZERO;

    private Integer stock = 0;
}
