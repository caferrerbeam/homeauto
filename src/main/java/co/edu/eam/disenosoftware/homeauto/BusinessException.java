package co.edu.eam.disenosoftware.homeauto;

public class BusinessException extends RuntimeException {

  private String codigo;

  public BusinessException(String message, String codigo) {
    super(message);
    this.codigo = codigo;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
}
