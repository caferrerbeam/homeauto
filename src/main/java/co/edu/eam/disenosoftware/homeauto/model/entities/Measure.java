package co.edu.eam.disenosoftware.homeauto.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Measure entity
 */
@Entity
@Table(name = "measures")
public class Measure implements Serializable {

  /**
   * Primary key
   */
  @Id
  private Long id;

  /**
   * value measured
   */
  private Double value;

  /**
   * when this mesaured was taken
   */
  @Column(name = "date_time")
  private Date dateTime;

  /**
   * channel who take the measured
   */
  @ManyToOne
  @JoinColumn(name = "channel_id", referencedColumnName = "id")
  private Channel channel;

  public Measure() {
  }
}
