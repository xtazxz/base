package io.github.xtazxz.base.entity;

import lombok.Data;

/**
 * 响应结果类.
 */
@Data
public class Response<T> {

  private Integer code;

  private String message;

  private T data;

  private Response() {
  }

  public static <T> Response<T> ok() {
    return ok(null);
  }

  public static <T> Response<T> ok(T data) {
    return error(ErrorCode.success(), data);
  }

  public static <T> Response<T> error(ErrorCode errorCode) {
    return error(errorCode, null);
  }

  public static <T> Response<T> error(ErrorCode errorCode, T data) {
    Response<T> r = new Response<>();
    r.setCode(errorCode.code());
    r.setMessage(errorCode.message());
    r.setData(data);
    return r;
  }

}
