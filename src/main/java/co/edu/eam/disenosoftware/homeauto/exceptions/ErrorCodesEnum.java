package co.edu.eam.disenosoftware.homeauto.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {

  ROOM_NOT_FOUND("0001",  HttpStatus.NOT_FOUND),
  CHANNEL_LIST_EMPTY("0002", "La lista de canales suministrada esta vacia"),
  MAX_BELOW_MIN("0003", HttpStatus.BAD_REQUEST),
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

  ErrorCodesEnum(String code, HttpStatus status) {
    this.code = code;
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
