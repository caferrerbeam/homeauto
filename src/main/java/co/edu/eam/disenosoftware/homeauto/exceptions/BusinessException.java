package co.edu.eam.disenosoftware.homeauto.exceptions;

public class BusinessException extends RuntimeException {

  private ErrorCodesEnum codigo;

  public BusinessException(String message, ErrorCodesEnum codigo) {
    super(message);
    this.codigo = codigo;
  }

  public ErrorCodesEnum getCodigo() {
    return codigo;
  }

  public void setCodigo(ErrorCodesEnum codigo) {
    this.codigo = codigo;
  }
}
