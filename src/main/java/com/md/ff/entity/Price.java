package com.md.ff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(schema = "FF",name = "price")
@Builder
public class Price {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "BRAND_ID")
  private Integer brandId;
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Column(name = "PRICE_LIST")
  private Integer priceList;
  @Column(name = "PRODUCT_ID")
  private Integer productId;
  @Column()
  private Integer priority;
  @Column()
  private BigDecimal price;
  @Column()
  private String currency;

  public static Specification<Price> productIdEquals(Integer value) {
    return (root, query, builder) -> builder.equal(root.get("productId"), value);
  }

  public static Specification<Price> brandIdEquals(Integer value) {
    return (root, query, builder) -> builder.equal(root.get("brandId"), value);
  }

  public static Specification<Price> dateBetween(String date) {
    LocalDateTime dateTime = LocalDateTime.parse(date);
    return (root, query, builder) -> {
      return builder.and(
          builder.lessThanOrEqualTo(root.get("startDate"), dateTime),
          builder.greaterThanOrEqualTo(root.get("endDate"), dateTime)
      );
    };
  }
}
