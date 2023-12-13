package io.github.xtazxz.base.web.autoconfigure;


import io.github.xtazxz.base.web.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;


public class WebAutoConfigure {

  @Bean
  @ConditionalOnProperty(name = "base.web.global-exception-handler-auto-configure",
      havingValue = "true")
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }

}
