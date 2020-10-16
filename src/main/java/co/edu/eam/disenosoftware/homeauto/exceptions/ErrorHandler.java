package co.edu.eam.disenosoftware.homeauto.exceptions;

import co.edu.eam.disenosoftware.homeauto.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exc) {
    ErrorResponse error = new ErrorResponse(exc.getCodigo().getCode(), exc.getMessage());
    return new ResponseEntity(error, exc.getCodigo().getStatus());
  }

}
