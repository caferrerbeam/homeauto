package co.edu.eam.disenosoftware.homeauto.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {

  ROOM_NOT_FOUND("0001", "El cuarto no existe", HttpStatus.NOT_FOUND),
  BAD_PARAMS("0003", "El cuarto no existe", HttpStatus.BAD_REQUEST),
  CHANNEL_LIST_EMPTY("0002", "La lista de canales suministrada esta vacia", HttpStatus.BAD_REQUEST)
  ;

  private String code;

  private String message;

  private HttpStatus status;

  ErrorCodesEnum(String code) {
    this.code = code;
  }

  ErrorCodesEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  ErrorCodesEnum(String code, String message, HttpStatus status) {
    this.code = code;
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
