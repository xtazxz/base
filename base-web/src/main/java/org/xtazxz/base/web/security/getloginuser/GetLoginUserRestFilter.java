package org.xtazxz.base.web.security.getloginuser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.xtazxz.base.entity.SecurityUser;
import org.xtazxz.base.web.security.SecurityBaseFilter;

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
