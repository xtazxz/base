package org.xtazxz.base.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xtazxz.base.common.component.BaseApplication;

@SpringBootApplication
public class DemoApplication extends BaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
