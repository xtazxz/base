package org.xtazxz.base.common.component;

public class BaseApplication {

  public static final String ENV_SPRING_PROFILES_ACTIVE = "spring_profiles_active";
  public static final String PROPERTY_SPRING_PROFILES_ACTIVE = "spring.profiles.active";

  static {
    // 默认激活local的profile
    String env = System.getenv(ENV_SPRING_PROFILES_ACTIVE);
    String property = System.getProperty(PROPERTY_SPRING_PROFILES_ACTIVE);
    if ((env == null || env.trim().length() == 0)
        && (property == null || property.trim().length() == 0)) {
      System.setProperty(PROPERTY_SPRING_PROFILES_ACTIVE, "local");
    }
  }

}
