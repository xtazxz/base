package org.xtazxz.base.web.security.password;

import cn.hutool.core.io.IoUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.xtazxz.base.common.ex.ErrorCodeException;
import org.xtazxz.base.common.utils.digest.DigestUtils;
import org.xtazxz.base.common.utils.json.JsonUtils;
import org.xtazxz.base.web.ec.ErrorCodeWebSecurity;
import org.xtazxz.base.web.security.AuthType;
import org.xtazxz.base.web.security.DefaultAuthentication;
import org.xtazxz.base.web.security.GrantedAuthorityService;
import org.xtazxz.base.web.security.SecurityBaseFilter;
import org.xtazxz.base.web.security.UserType;

public class PasswordLoginRestFilter extends SecurityBaseFilter {

  private final PasswordUserService passwordUserService;

  private final GrantedAuthorityService grantedAuthorityService;

  public PasswordLoginRestFilter(PasswordProperties passwordProperties,
      PasswordUserService passwordUserService, GrantedAuthorityService grantedAuthorityService) {
    this.antMatchers(passwordProperties.getLoginRestPath());
    this.passwordUserService = passwordUserService;
    this.grantedAuthorityService = grantedAuthorityService;
  }

  @Override
  public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      String baseUrl, String path) throws IOException {
    // 通过username拿到密码用户信息
    String requestBody = IoUtil.read(request.getInputStream(), Charset.defaultCharset());
    PasswordLoginRequestBody passwordLoginRequestBody = JsonUtils.toObject(requestBody,
        PasswordLoginRequestBody.class);
    PasswordUser passwordUser = passwordUserService.loadPasswordUser(
        request, passwordLoginRequestBody.getUsername());
    // 校验密码是否正确
    if (checkPassword(passwordLoginRequestBody, passwordUser)) {
      passwordUser.setPassword(null);
      passwordUser.setSalt(null);
      // 密码正确，则加载认证信息
      Authentication authentication = loadAuthentication(request, passwordUser);
      // 存放认证信息到上下文和session
      saveAuthentication(request, response, authentication);
      // 登陆成功，跳过后续的Filter，返回成功影响
      responseOk(response);
    } else {
      throw new ErrorCodeException(ErrorCodeWebSecurity.usernameOrPasswordError());
    }
  }

  private Authentication loadAuthentication(HttpServletRequest request, PasswordUser passwordUser) {
    // 密码正确，则创建认证用户
    passwordUser.setUserType(UserType.PERSON.name());
    passwordUser.setAuthType(AuthType.PASSWORD.name());
    passwordUser.setAuthSource("FRONTEND");
    // 加载权限列表
    Collection<GrantedAuthority> grantedAuthorities =
        grantedAuthorityService.loadGrantedAuthority(request, passwordUser);
    // 生成认证信息
    return new DefaultAuthentication(passwordUser, grantedAuthorities);
  }

  private boolean checkPassword(PasswordLoginRequestBody passwordLoginRequestBody,
      PasswordUser passwordUser) {
    String calcPassword = DigestUtils.encodeSha256(
        passwordLoginRequestBody.getPassword() + passwordUser.getSalt());
    return calcPassword.equals(passwordUser.getPassword());
  }

}
