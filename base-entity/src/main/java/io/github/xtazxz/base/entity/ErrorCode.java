package io.github.xtazxz.base.entity;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.helpers.MessageFormatter;

/**
 * 错误码.
 *
 * @param code           错误码
 * @param messagePattern 消息模板
 * @param argArray       模板参数
 */
public record ErrorCode(Integer code, String messagePattern, Object[] argArray) {

  // ====================通用错误码====================
  // 通用系统错误码
  public static final int EC_SYSTEM = -1;
  // 成功
  public static final int EC_SUCCESS = 0;
  // 通用业务错误码
  public static final int EC_BUSINESS = 1;

  // ====================通用业务逻辑的错误码(完全与业务无关)====================
  // 共6位，前3位代表模块，后3位错误码序号
  public static final int EC_COMMON = 100000;

  // ====================App Base的业务错误码(基础模块)====================
  // 共8位，前2位代表基础模块，中间3位代表子模块，后3位错误码序号
  public static final int EC_BASE = 10000000;

  // ====================自定义业务模块的错误码(具体的应用模块)====================
  // 共9位，前3位代表应用模块，中间3位代表子模块，后3位错误码序号
  public static final int EC_APP = 100000000;

  public static ErrorCode system(String msg) {
    return new ErrorCode(EC_SYSTEM, "{}", new Object[]{msg});
  }

  public static ErrorCode system(String messagePattern, Object... argArray) {
    String msg = MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
    return system(msg);
  }

  public static ErrorCode system() {
    return system("system error");
  }

  public static ErrorCode success(String msg) {
    return new ErrorCode(EC_SUCCESS, "{}", new Object[]{msg});
  }

  public static ErrorCode success(String messagePattern, Object... argArray) {
    String msg = MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
    return success(msg);
  }

  public static ErrorCode success() {
    return success("success");
  }

  public static ErrorCode business(String msg) {
    return new ErrorCode(EC_BUSINESS, "{}", new Object[]{msg});
  }

  public static ErrorCode business(String messagePattern, Object... argArray) {
    String msg = MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
    return business(msg);
  }

  public static ErrorCode business() {
    return business("business error");
  }

  public String message() {
    return MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorCode errorCode = (ErrorCode) o;
    return Objects.equals(code, errorCode.code)
        && Objects.equals(messagePattern, errorCode.messagePattern)
        && Arrays.equals(argArray, errorCode.argArray);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(code);
    result = 31 * result + Objects.hash(messagePattern);
    result = 31 * result + Arrays.hashCode(argArray);
    return result;
  }

  @Override
  public String toString() {
    return "ErrorCode{"
        + "code=" + code
        + ", messagePattern=" + messagePattern
        + ", argArray=" + Arrays.toString(argArray)
        + '}';
  }

}
