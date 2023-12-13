package org.xtazxz.base.web.security.password;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.xtazxz.base.web.security.SecurityBaseFilter;

public class PasswordLogoutRestFilter extends SecurityBaseFilter {

  public PasswordLogoutRestFilter(PasswordProperties passwordProperties) {
    this.antMatchers(passwordProperties.getLogoutRestPath());
  }

  @Override
  public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      String baseUrl, String path) throws IOException {
    // 从session中移除认证信息
    removeAuthentication(request, response);
    // 退出成功，跳过后续的Filter，请求结束
    responseOk(response);
  }

}
