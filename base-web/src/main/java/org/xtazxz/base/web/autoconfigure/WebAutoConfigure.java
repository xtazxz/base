package org.xtazxz.base.web.autoconfigure;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.xtazxz.base.web.GlobalExceptionHandler;


public class WebAutoConfigure {

  @Bean
  @ConditionalOnProperty(name = "base.web.global-exception-handler-auto-configure",
      havingValue = "true")
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }

}
