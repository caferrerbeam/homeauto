package co.edu.eam.disenosoftware.homeauto.model.requests;

public class UpdateSensorRequest {

  private String name;
  private String brand;
  private String type;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "UpdateSensorRequest{" +
            "name='" + name + '\'' +
            ", brand='" + brand + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}
