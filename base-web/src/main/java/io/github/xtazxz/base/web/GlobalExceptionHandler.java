package io.github.xtazxz.base.web;

import io.github.xtazxz.base.common.component.BaseComponent;
import io.github.xtazxz.base.common.ex.ErrorCodeException;
import io.github.xtazxz.base.entity.ErrorCode;
import io.github.xtazxz.base.entity.FieldValidationError;
import io.github.xtazxz.base.entity.Response;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseComponent {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Response<Void> error(Exception e) {
    Response<Void> response = Response.error(ErrorCode.system(e.getMessage()));
    logger.error(response.getMessage(), e);
    return response;
  }

  @ExceptionHandler(BindException.class)
  @ResponseBody
  public Response<List<FieldValidationError>> error(BindException e) {
    List<FieldValidationError> fieldValidationErrorList = new ArrayList<>();
    for (FieldError fieldError : e.getFieldErrors()) {
      fieldValidationErrorList.add(
          new FieldValidationError(fieldError.getField(), fieldError.getCode(),
              fieldError.getDefaultMessage()));
    }
    return Response.error(ErrorCode.business(), fieldValidationErrorList);
  }

  @ExceptionHandler(ErrorCodeException.class)
  @ResponseBody
  public Response<Void> error(ErrorCodeException e) {
    Response<Void> response = Response.error(e.getErrorCode());
    if (e.getErrorCode().code() < 0) {
      logger.error(response.getMessage(), e);
    }
    return response;
  }

}
