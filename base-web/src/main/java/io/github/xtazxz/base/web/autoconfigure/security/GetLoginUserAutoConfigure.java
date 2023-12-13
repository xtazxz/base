package io.github.xtazxz.base.web.autoconfigure.security;


import io.github.xtazxz.base.web.security.getloginuser.GetLoginUserRestFilter;
import io.github.xtazxz.base.web.security.getloginuser.LoginUserProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

@ConditionalOnClass(SecurityContextHolder.class)
@ConditionalOnProperty(name = "base.web.security.get-login-user.auto-configure",
    havingValue = "true")
public class GetLoginUserAutoConfigure {

  @Bean
  @ConfigurationProperties(prefix = "base.web.security.get-login-user")
  public LoginUserProperties loginUserProperties() {
    return new LoginUserProperties();
  }

  @Bean
  public GetLoginUserRestFilter getLoginUserRestFilter(LoginUserProperties loginUserProperties) {
    return new GetLoginUserRestFilter(loginUserProperties);
  }

}
