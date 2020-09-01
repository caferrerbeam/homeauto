package co.edu.eam.disenosoftware.homeauto.model.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Room entity
 */
@Entity
@Table(name = "rooms")
public class Room implements Serializable {

  /**
   * Primary key
   */
  @Id
  private Long id;

  /**
   * Room name
   */
  private String name;


  /**
   * sensonrs installed in this room
   */
  @OneToMany(mappedBy = "room")
  private List<Sensor> sensors;

  public Room() {
  }
}
