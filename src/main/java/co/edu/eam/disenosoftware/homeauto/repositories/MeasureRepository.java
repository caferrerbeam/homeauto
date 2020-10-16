package co.edu.eam.disenosoftware.homeauto.repositories;


import co.edu.eam.disenosoftware.homeauto.model.entities.Measure;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class MeasureRepository {

  @PersistenceContext
  private EntityManager em;

  public void create(Measure measure) {
    em.persist(measure);
  }

  /**
   * Method to find measure with values greate than parameter
   * @param valueGreaterThan parameter to evaluate
   * @return List of Measures
   */
  public List<Measure> getMeasureValueGreaterThan(double valueGreaterThan){

    String queryStr = "SELECT m FROM Measure m WHERE m.value > :value";
    Query query = em.createQuery(queryStr);
    query.setParameter("value", valueGreaterThan);

    return query.getResultList();
  }

  public List<Measure> getMeasureValueBetween(double min, double max){

    String queryStr = "SELECT m FROM Measure m WHERE m.value > :minValue AND m.value < :maxValue";
    //String queryStr = "SELECT m FROM Measure m WHERE m.value BETWEEN :minValue AND :maxValue";
    Query query = em.createQuery(queryStr);
    query.setParameter("minValue", min);
    query.setParameter("maxValue", max);

    return query.getResultList();
  }

  public List<Measure> getMeasuresByRoomId(Long id) {
    String queryStr = "SELECT m FROM Measure m WHERE m.channel.sensor.room.id = :roomId";
    Query query = em.createQuery(queryStr);
    query.setParameter("roomId", id);

    return query.getResultList();
  }
}
