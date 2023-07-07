package com.md.ff.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseDto(Integer productId, Integer brandId, Integer priceList,
                               LocalDateTime startDate,
                               LocalDateTime endDate, BigDecimal price) {

}
