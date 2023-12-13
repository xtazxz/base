package org.xtazxz.base.web.component;

import org.springframework.security.core.context.SecurityContextHolder;
import org.xtazxz.base.common.component.BaseComponent;
import org.xtazxz.base.entity.SecurityUser;

public class BaseController extends BaseComponent {

  /**
   * 获取当前认证用户.
   *
   * @param <T> 认证用户
   */
  @SuppressWarnings("unchecked")
  public <T extends SecurityUser> T getSecurityUser() {
    Object securityUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (securityUser == null || securityUser.equals("anonymousUser")) {
      return null;
    }
    return (T) securityUser;
  }

  /**
   * 是否登录.
   */
  public boolean isLogin() {
    return getSecurityUser() != null;
  }

}
