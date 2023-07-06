package com.md.ff.service;

import com.md.ff.dto.PriceFilterDto;
import com.md.ff.dto.PriceResponseDto;

public interface PriceService {

  PriceResponseDto getPrice(PriceFilterDto filters);

}
