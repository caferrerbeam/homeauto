package co.edu.eam.disenosoftware.homeautobackend.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Channel entity.
 */
@Entity
@Table(name = "channels")
public class Channel implements Serializable {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Channel number.
   */
  @Column(name = "channel_number")
  private Integer channelNumber;

  /**
   * Min value of the sensor.
   */
  @Column(name = "min_value")
  private Double minValue;

  /**
   * Max value of the sensor.
   */
  @Column(name = "max_value")
  private Double maxValue;

  /**
   * Sensor owner of the channel.
   */
  @ManyToOne
  @JoinColumn(name = "sensor_id", referencedColumnName = "id")
  private Sensor sensor;
}
