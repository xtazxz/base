package org.xtazxz.base.common.ex;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;
import org.xtazxz.base.entity.ErrorCode;

public class ErrorCodeException extends RuntimeException {

  @Getter
  protected final transient ErrorCode errorCode;

  // 大于0的ErrorCode, 代表业务或者规则异常，不用打印堆栈（所以，不用cause）
  public ErrorCodeException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  // 小于0的ErrorCode, 代表系统或者代码异常，会打印堆栈（所以，都需要cause）
  public ErrorCodeException(Throwable cause, ErrorCode errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ErrorCodeException(Throwable cause, String message) {
    super(message, cause);
    errorCode = ErrorCode.system(message);
  }

  public ErrorCodeException(Throwable cause) {
    this(cause, cause.getMessage());
  }

  public ErrorCodeException(Throwable cause, String messagePattern, Object... argArray) {
    this(cause, MessageFormatter.arrayFormat(messagePattern, argArray).getMessage());
  }

  @Override
  public String getMessage() {
    return errorCode.message();
  }

}
