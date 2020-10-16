package co.edu.eam.disenosoftware.homeauto.controllers;

import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.model.requests.CreateSensorRequest;
import co.edu.eam.disenosoftware.homeauto.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  @Autowired
  private SensorService sensorService;

  // URL = /sensors,
  @GetMapping  //indico que es get...
  public List<Sensor> getAllSensor() {
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
    sensorService.create(request.getName(), request.getType(), request.getBrand(), request.getRoomId(), request.getMin(), request.getMax());
  }

  //@PostMapping
  public void create(@RequestBody Sensor sensor) {

  }

  /**
   * URL /sensors/by-name?name_sensor=xxx
   *
   * @param name
   * @return
   */
  @GetMapping("/by-name")
  public List<Sensor> getSensorsByName(@RequestParam("name_sensor") String name) {
    return sensorService.getByName(name);
  }

  /**
   * buscar un sensor por id
   * URL: /sensors/{id_sensor}
   */

  @GetMapping("/{idSensor}")
  public Sensor find(@PathVariable("idSensor") Long id) {
    return sensorService.find(id);
  }

  /**
   * URL: /sensors/{id}/channels/{idchanenl}
   */
  @GetMapping("/{idSensor}/channels/{idChannel}")
  public Channel getChanelBySensor(@PathVariable Long idSensor, @PathVariable Long idChannel) {
    return null;
  }

  /**
   * URL: /sensors/{idSensor}
   * param: pathParam idSensor
   * body: {name, type, brand}
   * Verb: PUT
   */
  @PutMapping("/{id}")
  public void updateSensor(@PathVariable Long id, @RequestBody Sensor body) {
    System.out.println(id);
    System.out.println(body);
  }


}
