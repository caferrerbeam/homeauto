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
 * Sensor entity
 */
@Entity
@Table(name = "sensors")
public class Sensor implements Serializable {
  /**
   * Primary key
   */
  @Id
  private Long id;

  /**
   * Sensor name
   */
  private String name;

  /**
   * Sensor type
   */
  private String type;

  /**
   * sensor brand
   */
  private String brand;

  /**
   * sensor channels
   */
  @OneToMany(mappedBy = "sensor")
  private List<Channel> channels;


  /**
   * Room onwns this sensor
   */
  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  public Sensor() {
  }
}
