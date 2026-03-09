package nl.les12vinylshopdto.les12vinylshopdto.dto.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record StockRequestDTO(

        @NotNull
        String condition,

        @Positive
        BigDecimal price

) {}

