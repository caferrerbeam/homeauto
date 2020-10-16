package co.edu.eam.disenosoftware.homeauto.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * value measured
   */
  private Double value;

  /**
   * when this mesaured was taken
   */
  //@Temporal(TemporalType.DATE)
  @Column(name = "date_time")
  private Date dateTime;

  /**
   * channel who take the measured
   */
  @ManyToOne
  @JoinColumn(name = "channel_id", referencedColumnName = "id")
  @JsonBackReference
  private Channel channel;

  public Measure() {
  }

  public Measure(Long id, Double value, Date dateTime, Channel channel) {
    this.id = id;
    this.value = value;
    this.dateTime = dateTime;
    this.channel = channel;
  }

  public Measure(Double value, Channel channel) {
    this.value = value;
    this.channel = channel;
    dateTime = new Date();
  }

  @Override
  public String toString() {
    return "Measure{" +
            "id=" + id +
            ", value=" + value +
            ", dateTime=" + dateTime +
            ", channel=" + channel +
            '}';
  }
}
