package org.xtazxz.base.web.security.password;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xtazxz.base.entity.SecurityUser;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordUser extends SecurityUser {

  private String password;

  private String salt;

}
