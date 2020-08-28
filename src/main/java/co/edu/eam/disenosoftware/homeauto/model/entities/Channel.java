package co.edu.eam.disenosoftware.homeauto.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel implements Serializable {

  @Id
  private Long id;

  private String name;

  private Double max;

  private Double min;

  @OneToMany(mappedBy = "channel")
  private List<Measure> measures;

  @ManyToOne
  @JoinColumn(name = "sensor_id", referencedColumnName = "id" )
  private Sensor sensor;

  public Channel() {
  }

  public Channel(String name, Double max, Double min) {
    this.name = name;
    this.max = max;
    this.min = min;
  }
}
