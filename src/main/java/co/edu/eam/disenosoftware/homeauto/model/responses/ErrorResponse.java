package co.edu.eam.disenosoftware.homeauto.model.responses;

public class ErrorResponse {

  private String message;
  private String code;

  public ErrorResponse() {
  }

  public ErrorResponse(String message, String code) {
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
