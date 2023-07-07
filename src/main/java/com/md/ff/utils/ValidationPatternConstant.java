package com.md.ff.utils;

public class ValidationPatternConstant {

  public static final String LOCAL_DATE_TIME_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$";

  private ValidationPatternConstant() {
    throw new IllegalStateException("Utility class");
  }
}