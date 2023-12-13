package org.xtazxz.base.web.autoconfigure.security;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.xtazxz.base.web.security.GrantedAuthorityService;
import org.xtazxz.base.web.security.password.PasswordLoginRestFilter;
import org.xtazxz.base.web.security.password.PasswordLogoutRestFilter;
import org.xtazxz.base.web.security.password.PasswordProperties;
import org.xtazxz.base.web.security.password.PasswordUserService;

@ConditionalOnProperty(name = "base.web.security.password.auto-configure", havingValue = "true")
public class PasswordAutoConfigure {

  @Bean
  @ConfigurationProperties(prefix = "base.web.security.password")
  public PasswordProperties passwordProperties() {
    return new PasswordProperties();
  }

  @Bean
  public PasswordLoginRestFilter passwordLoginFilter(PasswordProperties passwordProperties,
      PasswordUserService passwordUserService, GrantedAuthorityService grantedAuthorityService) {
    return new PasswordLoginRestFilter(passwordProperties, passwordUserService,
        grantedAuthorityService);
  }

  @Bean
  public PasswordLogoutRestFilter passwordLogoutFilter(PasswordProperties passwordProperties) {
    return new PasswordLogoutRestFilter(passwordProperties);
  }

}
