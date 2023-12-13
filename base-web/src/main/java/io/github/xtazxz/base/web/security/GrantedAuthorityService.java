package io.github.xtazxz.base.web.security;

import io.github.xtazxz.base.entity.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

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
