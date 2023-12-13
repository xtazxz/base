package org.xtazxz.base.web.security;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.xtazxz.base.entity.SecurityUser;

public interface GrantedAuthorityService {

  /**
   * 加载认证用户权限.
   *
   * @param request      请求
   * @param securityUser 认证用户
   */
  Collection<GrantedAuthority> loadGrantedAuthority(HttpServletRequest request,
      SecurityUser securityUser);

}
