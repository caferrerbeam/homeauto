package co.edu.eam.disenosoftware.homeauto.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor implements Serializable {

  @Id
  private Long id;

  private String name;

  private String type;

  private String brand;

  @OneToMany(mappedBy = "sensor")
  private List<Channel> channels;

  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  public Sensor() {
  }
}
