package org.xtazxz.base.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.xtazxz.base.entity.SecurityUser;
import org.xtazxz.base.web.component.BaseFilter;

public abstract class SecurityBaseFilter extends BaseFilter {

  /**
   * 保存认证信息到session
   */
  public void saveAuthentication(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    // 重置session（必须重新获取一次session）
    request.getSession().invalidate();
    request.getSession();
    // 存放认证信息到session
    SecurityContextHolder.getContext().setAuthentication(authentication);
    new HttpSessionSecurityContextRepository().saveContext(SecurityContextHolder.getContext(),
        request, response);
  }

  /**
   * 从session中移除认证信息
   */
  public void removeAuthentication(HttpServletRequest request, HttpServletResponse response) {
    // 从session中移除认证信息
    SecurityContextHolder.getContext().setAuthentication(null);
    new HttpSessionSecurityContextRepository().saveContext(SecurityContextHolder.getContext(),
        request, response);
    // 重置session（必须重新获取一次session）
    request.getSession().invalidate();
    request.getSession();
  }

  /**
   * 获取认证用户
   */
  @SuppressWarnings("unchecked")
  public <T extends SecurityUser> T getSecurityUser() {
    Object securityUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (securityUser == null || securityUser.equals("anonymousUser")) {
      return null;
    }
    return (T) securityUser;
  }

}
