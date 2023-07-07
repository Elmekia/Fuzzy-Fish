package com.md.ff.controller;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.md.ff.dto.PriceResponseDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class PriceControllerTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(
      new JavaTimeModule());

  private final MockMvc mockMvc;

  @Autowired
  PriceControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test1() throws Exception {
    String price = mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-14T10:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
        .getResponse()
        .getContentAsString();
    PriceResponseDto priceDto = OBJECT_MAPPER.readValue(price, PriceResponseDto.class);
    PriceResponseDto expectedPriceDto = new PriceResponseDto(35455, 1, 1,
        LocalDateTime.parse("2020-06-14T00:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(
        (35.50)).setScale(2, RoundingMode.HALF_UP));

    assertEquals("responseDto is not the expected one", expectedPriceDto, priceDto);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test2() throws Exception {
    String price = mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-14T16:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
        .getResponse()
        .getContentAsString();
    PriceResponseDto priceDto = OBJECT_MAPPER.readValue(price, PriceResponseDto.class);

    PriceResponseDto expectedPriceDto = new PriceResponseDto(35455, 1, 2,
        LocalDateTime.parse("2020-06-14T15:00:00"),
        LocalDateTime.parse("2020-06-14T18:30:00"), BigDecimal.valueOf(
        (25.45)).setScale(2, RoundingMode.HALF_UP));

    assertEquals("responseDto is not the expected one", expectedPriceDto, priceDto);
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test3() throws Exception {
    String price = mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-14T21:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
        .getResponse()
        .getContentAsString();
    PriceResponseDto priceDto = OBJECT_MAPPER.readValue(price, PriceResponseDto.class);

    PriceResponseDto expectedPriceDto = new PriceResponseDto(35455, 1, 1,
        LocalDateTime.parse("2020-06-14T00:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(
        (35.50)).setScale(2, RoundingMode.HALF_UP));

    assertEquals("responseDto is not the expected one", expectedPriceDto, priceDto);
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
  void test4() throws Exception {
    String price = mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-15T10:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
        .getResponse()
        .getContentAsString();
    PriceResponseDto priceDto = OBJECT_MAPPER.readValue(price, PriceResponseDto.class);

    PriceResponseDto expectedPriceDto = new PriceResponseDto(35455, 1, 3,
        LocalDateTime.parse("2020-06-15T00:00:00"),
        LocalDateTime.parse("2020-06-15T11:00:00"), BigDecimal.valueOf(
        (30.50)).setScale(2, RoundingMode.HALF_UP));

    assertEquals("responseDto is not the expected one", expectedPriceDto, priceDto);
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
  void test5() throws Exception {
    String price = mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-16T21:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
        .getResponse()
        .getContentAsString();
    PriceResponseDto priceDto = OBJECT_MAPPER.readValue(price, PriceResponseDto.class);

    PriceResponseDto expectedPriceDto = new PriceResponseDto(35455, 1, 4,
        LocalDateTime.parse("2020-06-15T16:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(
        (38.95)).setScale(2, RoundingMode.HALF_UP));

    assertEquals("responseDto is not the expected one", expectedPriceDto, priceDto);
  }

  @Test
  @DisplayName("Test 6: petición con parametros incorrectos (mal formato de fecha y 0 en los id")
  void test6() throws Exception {
    mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-16-21:00:00")
                .queryParam("productId", "0")
                .queryParam("brandId", "0"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.date").value("Invalid date format"))
        .andExpect(jsonPath("$.productId").value("must be greater than 0"))
        .andExpect(jsonPath("$.brandId").value("must be greater than 0"));
  }

  @Test
  @DisplayName("Test 7: petición con parametros null")
  void test7() throws Exception {
    mockMvc.perform(
            get("/prices"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.date").value("must not be null"))
        .andExpect(jsonPath("$.productId").value("must not be null"))
        .andExpect(jsonPath("$.brandId").value("must not be null"));
  }

  @Test
  @DisplayName("Test 8: petición que no devuelva resultados")
  void test8() throws Exception {
    mockMvc.perform(
            get("/prices")
                .queryParam("date", "2020-06-16T21:00:00")
                .queryParam("productId", "35456")
                .queryParam("brandId", "2"))
        .andExpect(status().isOk())
        .andExpect(content().string(""));
  }

}