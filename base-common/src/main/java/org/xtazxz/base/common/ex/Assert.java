package org.xtazxz.base.common.ex;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.xtazxz.base.entity.ErrorCode;

public class Assert {

  private Assert() {
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public static <T> T getOptional(Optional<T> optional, ErrorCode errorCode) {
    if (optional.isEmpty()) {
      throw new ErrorCodeException(errorCode);
    }
    return optional.get();
  }

  public static void isTrue(boolean expression, ErrorCode errorCode) {
    if (!expression) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void isNull(Object object, ErrorCode errorCode) {
    if (object != null) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void notNull(Object object, ErrorCode errorCode) {
    if (object == null) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void hasLength(String text, ErrorCode errorCode) {
    if (!StringUtils.hasLength(text)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void hasText(String text, ErrorCode errorCode) {
    if (!StringUtils.hasText(text)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void doesNotContain(String textToSearch, String substring,
      ErrorCode errorCode) {
    if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
        && textToSearch.contains(substring)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void notEmpty(Object[] array, ErrorCode errorCode) {
    if (ObjectUtils.isEmpty(array)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void notEmpty(Map<?, ?> map, ErrorCode errorCode) {
    if (CollectionUtils.isEmpty(map)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void noNullElements(Object[] array, ErrorCode errorCode) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new ErrorCodeException(errorCode);
        }
      }
    }
  }

  public static void noNullElements(Collection<?> collection, ErrorCode errorCode) {
    if (collection != null) {
      for (Object element : collection) {
        if (element == null) {
          throw new ErrorCodeException(errorCode);
        }
      }
    }
  }

  public static void isInstanceOf(Class<?> type, Object obj, ErrorCode errorCode) {
    if (!type.isInstance(obj)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void isAssignable(Class<?> superType, Class<?> subType, ErrorCode errorCode) {
    if (subType == null || !superType.isAssignableFrom(subType)) {
      throw new ErrorCodeException(errorCode);
    }
  }

  public static void error(ErrorCode errorCode) {
    throw new ErrorCodeException(errorCode);
  }

}
