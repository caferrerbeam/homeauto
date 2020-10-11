package co.edu.eam.disenosoftware.homeauto.model.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateSensorRequest {


  @NotNull
  @NotEmpty
  @NotBlank
  @Size(min = 3, max=10)
  private String name;

  @NotNull
  @NotEmpty
  @NotBlank
  @Size(min = 3, max=10)
  private String type;

  @NotNull
  @NotEmpty
  @NotBlank
  @Size(min = 3, max=10)
  private String brand;

  @NotNull
  private Long roomId;

  @Min(value = 0)
  private Double min;

  @Min(value = 0)
  private Double max;

  public CreateSensorRequest() {
  }

  public CreateSensorRequest(@NotNull @NotEmpty @NotBlank @Size(min = 3, max = 10) String name,
                             @NotNull @NotEmpty @NotBlank @Size(min = 3, max = 10) String type,
                             @NotNull @NotEmpty @NotBlank @Size(min = 3, max = 10) String brand,
                             @NotNull Long roomId,
                             @Min(value = 0) Double min,
                             @Min(value = 0) Double max) {
    this.name = name;
    this.type = type;
    this.brand = brand;
    this.roomId = roomId;
    this.min = min;
    this.max = max;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

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

  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public Double getMin() {
    return min;
  }

  public void setMin(Double min) {
    this.min = min;
  }

  public Double getMax() {
    return max;
  }

  public void setMax(Double max) {
    this.max = max;
  }
}
