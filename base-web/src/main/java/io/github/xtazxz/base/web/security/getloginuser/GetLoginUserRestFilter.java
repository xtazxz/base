package io.github.xtazxz.base.web.security.getloginuser;

import io.github.xtazxz.base.entity.SecurityUser;
import io.github.xtazxz.base.web.security.SecurityBaseFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLoginUserRestFilter extends SecurityBaseFilter {

  public GetLoginUserRestFilter(LoginUserProperties loginUserProperties) {
    this.antMatchers(loginUserProperties.getGetLoginUserRestPath());
  }

  @Override
  public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      String baseUrl, String path) throws IOException {
    SecurityUser securityUser = getSecurityUser();
    if (securityUser == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    responseOk(response, securityUser);
  }

}
