package co.edu.eam.disenosoftware.homeautobackend.services;

import co.edu.eam.disenosoftware.homeauto.exceptions.ErrorCodes;
import co.edu.eam.disenosoftware.homeauto.exceptions.GenericBusinessException;
import co.edu.eam.disenosoftware.homeauto.model.entities.Measure;
import co.edu.eam.disenosoftware.homeauto.services.MeasureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@SpringBootTest
public class MeasuresServiceTest {

  @Autowired
  private MeasureService measureService;

  @PersistenceContext
  private EntityManager em;

  @Test
  @Sql({"/testdata/create_measure.sql"})
  public void testCreateMeasure(){

    measureService.createMeasure(1L, 1L, 3);

    List<Measure> measures = em.createQuery("SELECT m From Measure m WHERE m.channel.id = 1").getResultList();

    Assertions.assertEquals(1, measures.size());
  }

  @Test
  @Sql({"/testdata/create_measure.sql"})
  public void testCreateMeasureNotFoundSensor(){
    GenericBusinessException exception = Assertions.assertThrows(GenericBusinessException.class,
          () -> measureService.createMeasure(10L, 1L, 3) );

    Assertions.assertEquals(ErrorCodes.SENSOR_NOT_FOUND, exception.getCode());
    List<Measure> measures = em.createQuery("SELECT m From Measure m WHERE m.channel.id = 1").getResultList();
    Assertions.assertEquals(0, measures.size());
  }
}
