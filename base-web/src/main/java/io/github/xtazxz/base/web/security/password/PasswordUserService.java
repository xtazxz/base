package io.github.xtazxz.base.web.security.password;

import jakarta.servlet.http.HttpServletRequest;

public interface PasswordUserService {

  /**
   * 加载用户
   *
   * @param request  请求
   * @param username 登陆用户名
   */
  PasswordUser loadPasswordUser(HttpServletRequest request, String username);

}
