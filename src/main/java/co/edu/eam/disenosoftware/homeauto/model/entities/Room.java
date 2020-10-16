package co.edu.eam.disenosoftware.homeauto.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Room name
   */
  private String name;


  /**
   * sensonrs installed in this room
   */
  @OneToMany(mappedBy = "room")
  @JsonManagedReference
  private List<Sensor> sensors;

  public Room() {
  }

  /**
   * Constructor
   * @param id
   * @param name
   */
  public Room(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Room(String name) {
    this.name = name;
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

  public List<Sensor> getSensors() {
    return sensors;
  }

  public void setSensors(List<Sensor> sensors) {
    this.sensors = sensors;
  }

  @Override
  public String toString() {
    return "Room{"
            + "id=" + id
            + ", name='" + name + '\''
            + '}';
  }
}
