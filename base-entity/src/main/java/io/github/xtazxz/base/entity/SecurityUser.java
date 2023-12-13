package io.github.xtazxz.base.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * 认证用户.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityUser implements Serializable {

  private String userType;

  private String authSource;

  private String authType;

  private String id;

  private String name;

  private String username;

  private Map<String, String> attributes;

  public void addAttribute(String name, String value) {
    if (attributes == null) {
      attributes = new LinkedHashMap<>();
    }
    attributes.put(name, value);
  }

  public String getAttribute(String name) {
    if (attributes != null) {
      return attributes.get(name);
    }
    return null;
  }

}
