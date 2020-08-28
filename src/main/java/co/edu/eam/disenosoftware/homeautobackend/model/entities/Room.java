package co.edu.eam.disenosoftware.homeautobackend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Entity room.
 */
@Entity
@Table(name = "rooms")
public class Room implements Serializable {

  /**
   * room's id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Room's name.
   */
  private String name;

  /**
   * sensors of this room
   */
  @OneToMany(mappedBy = "room")
  private List<Sensor> sensors;

  /**
   * Constructor.
   */
  public Room() {
  }

  /**
   * Constructor.
   *
   * @param id,   room's id.
   * @param name, room's name.
   */
  public Room(Long id, String name) {
    this.id = id;
    this.name = name;
  }


  /**
   * Get id.
   *
   * @return room's id.
   */
  public Long getId() {
    return id;
  }

  /**
   * set room's id.
   *
   * @param id room's id.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * get room's name.
   *
   * @return room's name.
   */
  public String getName() {
    return name;
  }

  /**
   * set room's name.
   *
   * @param name room's name.
   */
  public void setName(String name) {
    this.name = name;
  }
}
