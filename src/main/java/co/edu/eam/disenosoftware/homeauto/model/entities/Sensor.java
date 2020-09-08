package co.edu.eam.disenosoftware.homeauto.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  @OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER)
  private List<Channel> channels;


  /**
   * Room onwns this sensor
   */
  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  public Sensor() {
  }

  public Sensor(Long id, String name, String type, String brand, Room room) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.brand = brand;
    this.room = room;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public List<Channel> getChannels() {
    return channels;
  }

  public void setChannels(List<Channel> channels) {
    this.channels = channels;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  @Override
  public String toString() {
    return "Sensor{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", brand='" + brand + '\'' +
            ", room=" + room +
            '}';
  }
}
