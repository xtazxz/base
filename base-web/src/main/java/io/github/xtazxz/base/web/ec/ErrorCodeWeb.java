package io.github.xtazxz.base.web.ec;

import io.github.xtazxz.base.entity.ErrorCode;

public class ErrorCodeWeb {

  // ====================App Base的业务错误码====================
  // 共8位，前2位代表模块，中间3位代表子模块，后3位错误码序号
  public static final int EC_BASE_WEB = ErrorCode.EC_BASE + 2000000;

  public static final int EC_BASE_WEB_SECURITY = EC_BASE_WEB + 1000;

}
