package co.edu.eam.disenosoftware.homeautobackend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Device entity.
 */
@Entity
@Table(name = "sensors")
public class Sensor implements Serializable {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Sensor type
   */
  private String type;

  /**
   * sensor's trade.
   */
  private String trade;

  /**
   * Sensor's room
   */
  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  /**
   * channel List.
   */
  @OneToMany(mappedBy = "sensor")
  private List<Channel> channels;


}
