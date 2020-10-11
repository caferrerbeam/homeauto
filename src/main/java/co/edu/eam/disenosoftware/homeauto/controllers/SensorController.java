package co.edu.eam.disenosoftware.homeauto.controllers;

import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.model.requests.CreateSensorRequest;
import co.edu.eam.disenosoftware.homeauto.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  @Autowired
  private SensorService sensorService;

  // URL = /sensors,
  @GetMapping  //indico que es get...
  public List<Sensor> getAllSensor(){

    return sensorService.getAllSensors();

  }

  //String name, String type,String brand, Long roomId, Double min, Double max
  /**
   * URL: /sensors
   * Verb: POST
   * parametros: {name:, type, brand, room_id, min, max}
   */
  @PostMapping
  public void createSensor(@RequestBody CreateSensorRequest request) {
    sensorService.create(request.getName(),request.getType(), request.getBrand(),request.getRoomId(), request.getMin(), request.getMax());
  }

}
