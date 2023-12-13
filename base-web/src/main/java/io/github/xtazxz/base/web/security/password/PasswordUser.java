package io.github.xtazxz.base.web.security.password;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.xtazxz.base.entity.SecurityUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordUser extends SecurityUser {

  private String password;

  private String salt;

}
