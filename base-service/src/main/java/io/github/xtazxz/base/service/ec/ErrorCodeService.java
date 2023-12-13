package io.github.xtazxz.base.service.ec;

import io.github.xtazxz.base.entity.ErrorCode;

public class ErrorCodeService {

  // ====================App Base的业务错误码====================
  // 共8位，前2位代表模块，中间3位代表子模块，后3位错误码序号
  public static final int EC_BASE_SERVICE = ErrorCode.EC_BASE + 1000000;

}
