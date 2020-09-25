package co.edu.eam.disenosoftware.homeauto.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Sensors channel
 */
@Entity
@Table(name = "channels")
public class Channel implements Serializable {

  /**
   * Primary key
   */
  @Id
  private Long id;

  /**
   * Sensors name
   */
  private String name;

  /**
   * Sensors max value
   */
  private Double max;

  /**
   * Sensors min value
   */
  private Double min;

  /**
   * Mesasures taken by sensor in this channel
   */
  @OneToMany(mappedBy = "channel")
  private List<Measure> measures;

  /**
   * Channels sensor
   */
  @ManyToOne
  @JoinColumn(name = "sensor_id", referencedColumnName = "id")
  private Sensor sensor;

  public Channel() {
  }

  public Channel(String name, Double max, Double min) {
    this.name = name;
    this.max = max;
    this.min = min;
  }

  public Channel(Long id, String name, Double max, Double min, Sensor sensor) {
    this.id = id;
    this.name = name;
    this.max = max;
    this.min = min;
    this.sensor = sensor;
  }

  @Override
  public String toString() {
    return "Channel{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", max=" + max +
            ", min=" + min +
            ", sensor=" + sensor +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getMax() {
    return max;
  }

  public void setMax(Double max) {
    this.max = max;
  }

  public Double getMin() {
    return min;
  }

  public void setMin(Double min) {
    this.min = min;
  }

  public List<Measure> getMeasures() {
    return measures;
  }

  public void setMeasures(List<Measure> measures) {
    this.measures = measures;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }
}
