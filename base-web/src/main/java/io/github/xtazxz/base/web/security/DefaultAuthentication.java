package io.github.xtazxz.base.web.security;

import io.github.xtazxz.base.entity.SecurityUser;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 身份认证.
 */
public class DefaultAuthentication implements Authentication {

  // 认证用户
  private final SecurityUser securityUser;

  // 用户权限
  private final Collection<GrantedAuthority> authorities;

  // 是否已经认证
  private boolean authenticated = false;

  /**
   * 构造方法.
   *
   * @param securityUser 认证用户
   * @param authorities  权限列表
   */
  public DefaultAuthentication(SecurityUser securityUser,
      Collection<GrantedAuthority> authorities) {
    this.securityUser = securityUser;
    this.authorities = authorities;
    setAuthenticated(true);
  }

  @Override
  public String getCredentials() {
    return null;
  }

  @Override
  public SecurityUser getPrincipal() {
    return securityUser;
  }

  @Override
  public String getName() {
    return securityUser.getName();
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " ["
        + "Principal=" + this.getPrincipal() + ", "
        + "Credentials=[PROTECTED], "
        + "Authenticated=" + this.isAuthenticated() + ", "
        + "Details=" + this.getDetails() + ", "
        + "Granted Authorities=" + this.authorities
        + "]";
  }

}
