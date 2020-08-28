package co.edu.eam.disenosoftware.homeauto.model.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room implements Serializable {

  @Id
  private Long id;

  private String name;

  @OneToMany(mappedBy = "room")
  private List<Sensor> sensors;

  public Room() {
  }
}
