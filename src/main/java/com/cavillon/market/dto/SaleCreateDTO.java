package com.cavillon.market.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleCreateDTO {

    @NotEmpty(message = "La venta debe contener al menos un ítem")
    private List<SaleItemCreateDTO> items;

    @NotNull(message = "El valor del dólar no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor del dólar debe ser mayor a 0")
    @Digits(integer = 10, fraction = 4, message = "Formato inválido para el valor del dólar")
    private BigDecimal dollarValue;

}
