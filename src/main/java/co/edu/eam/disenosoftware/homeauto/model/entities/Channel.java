package co.edu.eam.disenosoftware.homeauto.model.entities;

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
}
