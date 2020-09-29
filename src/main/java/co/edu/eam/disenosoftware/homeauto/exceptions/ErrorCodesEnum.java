package co.edu.eam.disenosoftware.homeauto.exceptions;

public enum ErrorCodesEnum {

  ROOM_NOT_FOUND("0001", "El cuarto no existe"),
  CHANNEL_LIST_EMPTY("0002", "La lista de canales suministrada esta vacia")
  ;

  private String code;

  private String message;

  ErrorCodesEnum(String code) {
    this.code = code;
  }

  ErrorCodesEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }
}
