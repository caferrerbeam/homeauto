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
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
    ErrorResponse error = new ErrorResponse(exception.getMessage(), exception.getCodigo().getCode());
    return new ResponseEntity(error, exception.getCodigo().getStatus());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> handleBadParams(MethodArgumentNotValidException exception) {

    String message = "Error en " + exception.getBindingResult().getFieldError().getField() +
            ": " + exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    ErrorResponse error = new ErrorResponse(message, "400");
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
}
