package co.edu.eam.disenosoftware.homeauto.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class SensorRepository {

  /**
   * Entity manager
   */
  @PersistenceContext
  private EntityManager em;


  public void create(Sensor sensor) {
    em.persist(sensor);
  }


  public Sensor find(Long id) {
    return em.find(Sensor.class, id);
  }
}
