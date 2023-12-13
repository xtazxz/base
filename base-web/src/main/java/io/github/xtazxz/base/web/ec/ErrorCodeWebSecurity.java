package io.github.xtazxz.base.web.ec;

import io.github.xtazxz.base.entity.ErrorCode;

public class ErrorCodeWebSecurity {

  public static ErrorCode userNotExist() {
    return new ErrorCode(ErrorCodeWeb.EC_BASE_WEB_SECURITY + 1, "用户不存在", null);
  }

  public static ErrorCode usernameOrPasswordError() {
    return new ErrorCode(ErrorCodeWeb.EC_BASE_WEB_SECURITY + 2, "用户名或者密码错误", null);
  }

}
