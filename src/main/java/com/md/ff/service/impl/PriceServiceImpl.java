package com.md.ff.service.impl;

import static com.md.ff.utils.SpecificationsUtils.concatenate;

import com.md.ff.dto.PriceFilterDto;
import com.md.ff.dto.PriceResponseDto;
import com.md.ff.entity.Price;
import com.md.ff.repository.PriceRepository;
import com.md.ff.service.PriceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;
  private static final String SORT_BY_PRIORITY = "priority";

  public PriceServiceImpl(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public PriceResponseDto getPrice(PriceFilterDto filters) {

    List<Specification<Price>> specs = new ArrayList<>();

    if (filters.date() != null) {
      specs.add(Price.dateBetween(filters.date()));
    }

    if (filters.productId() != null) {
      specs.add(Price.productIdEquals(filters.productId()));
    }

    if (filters.brandId() != null) {
      specs.add(Price.brandIdEquals(filters.brandId()));
    }

    Pageable pageable = PageRequest.of(0, 1, Sort.by(SORT_BY_PRIORITY).descending());

    List<Price> priceList = this.priceRepository.findAll(concatenate(specs), pageable).getContent();
    if (priceList.isEmpty()) {
      return null;
    }
    Price price = priceList.get(0);
    return new PriceResponseDto(price.getProductId(), price.getBrandId(), price.getPriceList(),
        price.getStartDate(), price.getEndDate(), price.getPrice());
  }
}
