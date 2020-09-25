package co.edu.eam.disenosoftware.homeauto.exceptions;

public class GenericBusinessException extends  RuntimeException {

  private ErrorCodes code;
  private String message;

  public GenericBusinessException(String message, ErrorCodes code) {
    super(message);
    this.code = code;
  }

  public ErrorCodes getCode() {
    return code;
  }

  public void setCode(ErrorCodes code) {
    this.code = code;
  }
}
