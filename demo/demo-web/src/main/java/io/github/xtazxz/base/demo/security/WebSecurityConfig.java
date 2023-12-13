package io.github.xtazxz.base.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> {
      requests.requestMatchers("/rest/login").permitAll();
      requests.requestMatchers("/rest/loginUser").permitAll();
      requests.requestMatchers("/rest/*").authenticated();
      requests.anyRequest().permitAll();
    });
    http.logout(AbstractHttpConfigurer::disable);
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }

}