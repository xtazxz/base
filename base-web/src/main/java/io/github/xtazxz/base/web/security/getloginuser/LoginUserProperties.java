package io.github.xtazxz.base.web.security.getloginuser;

import lombok.Data;

@Data
public class LoginUserProperties {

  /**
   * 获取登录用户信息的REST路径
   */
  private String getLoginUserRestPath = "GET /rest/loginUser";

}
