package co.edu.eam.disenosoftware.homeauto.exceptions;

public enum ErrorCodes {
  SENSOR_NOT_FOUND("sensor_not_found"),
  CHANNEL_NOT_FOUND("channel_not_found"),
  CHANNEL_NOT_IN_SENSOR("channel_not_in_sensor");

  private String code;

  ErrorCodes(String code) {
    this.code = code;
  }
}
