package org.xtazxz.base.web.security.password;

import lombok.Data;

@Data
public class PasswordLoginRequestBody {

  private String username;

  private String password;

}
