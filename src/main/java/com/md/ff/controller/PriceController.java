package com.md.ff.controller;

import com.md.ff.dto.PriceFilterDto;
import com.md.ff.dto.PriceResponseDto;
import com.md.ff.service.PriceService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Validated
public class PriceController {

  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping("/prices")
  PriceResponseDto getPrice(@Valid PriceFilterDto filters){
    return this.priceService.getPrice(filters);
  }

}
