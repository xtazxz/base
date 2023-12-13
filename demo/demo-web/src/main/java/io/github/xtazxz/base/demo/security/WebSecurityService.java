package io.github.xtazxz.base.demo.security;

import io.github.xtazxz.base.common.component.BaseComponent;
import io.github.xtazxz.base.entity.SecurityUser;
import io.github.xtazxz.base.web.security.GrantedAuthorityService;
import io.github.xtazxz.base.web.security.password.PasswordUser;
import io.github.xtazxz.base.web.security.password.PasswordUserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class WebSecurityService extends BaseComponent implements PasswordUserService,
    GrantedAuthorityService {

  @Override
  public PasswordUser loadPasswordUser(HttpServletRequest request, String username) {
    PasswordUser user = new PasswordUser();
    user.setId("100000001");
    user.setName("xutao1");
    user.setUsername(username);
    user.setPassword("958d51602bbfbd18b2a084ba848a827c29952bfef170c936419b0922994c0589");
    user.setSalt("123456");
    return user;
  }

  @Override
  public Collection<GrantedAuthority> loadGrantedAuthority(HttpServletRequest request,
      SecurityUser securityUser) {
    Collection<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
    grantedAuthoritys.add(new SimpleGrantedAuthority("USER"));
    return grantedAuthoritys;
  }

}
