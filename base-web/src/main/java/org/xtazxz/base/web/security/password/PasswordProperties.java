package org.xtazxz.base.web.security.password;

import lombok.Data;

@Data
public class PasswordProperties {

  private String loginRestPath = "POST /rest/login";

  private String logoutRestPath = "GET /rest/logout";

}
