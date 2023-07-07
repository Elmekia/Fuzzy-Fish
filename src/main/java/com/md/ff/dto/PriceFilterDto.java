package com.md.ff.dto;

import static com.md.ff.utils.ValidationPatternConstant.LOCAL_DATE_TIME_REGEX;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record PriceFilterDto(
    @Pattern(regexp = LOCAL_DATE_TIME_REGEX, message = "Invalid date format") @NotNull String date,
    @NotNull
    @Positive Integer productId,
    @NotNull
    @Positive Integer brandId) {

}