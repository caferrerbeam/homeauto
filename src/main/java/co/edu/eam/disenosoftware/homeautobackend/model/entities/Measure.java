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
import java.util.Date;

/**
 * Measures table
 */
@Table(name = "measures")
@Entity
public class Measure implements Serializable {

  /**
   * Primary key
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * measure value
   */
  private Double value;

  /**
   * Measure date time
   */
  @Column(name = "date_time")
  private Date dateTime;

  /**
   * channel owner of the measure
   */
  @ManyToOne
  @JoinColumn(name = "channel_id", referencedColumnName = "id")
  private Channel channel;

  public Measure() {
  }

  public Measure(Double value) {
    this.value = value;
  }
}
