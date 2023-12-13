package org.xtazxz.base.common.ec;


import org.xtazxz.base.entity.ErrorCode;

public class ErrorCodeCommonValidation {

  // ====================通用业务逻辑的错误码(完全与业务无关)====================
  // 共6位，前3位代表模块，后3位错误码序号
  public static final int EC_COMMON_VALIDATION = ErrorCode.EC_COMMON + 1000;
  public static final int EC_COMMON_DATABASE = ErrorCode.EC_COMMON + 2000;

}
