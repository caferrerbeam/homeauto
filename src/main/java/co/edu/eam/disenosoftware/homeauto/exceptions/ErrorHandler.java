package co.edu.eam.disenosoftware.homeauto.exceptions;

import co.edu.eam.disenosoftware.homeauto.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exc) {
    ErrorResponse error = new ErrorResponse(exc.getCodigo().getCode(), exc.getMessage());
    return new ResponseEntity(error, exc.getCodigo().getStatus());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> handleBusinessException(MethodArgumentNotValidException exception) {
    String message = "Error en " + exception.getBindingResult().getFieldError().getField() +
            ": " + exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    ErrorResponse error = new ErrorResponse("1000", message);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

}
