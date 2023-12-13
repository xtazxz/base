package org.xtazxz.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationError {

  private String field;

  private String code;

  private String message;

}
