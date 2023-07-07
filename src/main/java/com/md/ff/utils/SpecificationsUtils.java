package com.md.ff.utils;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class SpecificationsUtils {

  private SpecificationsUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * @param specs List of Specification to be concatenated
   * @return List of Specification concatenated
   */
  public static <T> Specification<T> concatenate(List<Specification<T>> specs) {
    Specification<T> ret = Specification.where(null);
    for (Specification<T> spec : specs) {
      ret = ret.and(spec);
    }
    return ret;
  }

}
