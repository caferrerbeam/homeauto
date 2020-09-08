package co.edu.eam.disenosoftware.homeautobackend.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Measure;
import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.repositories.MeasureRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Transactional
@SpringBootTest
public class MeasureRepositoryTest {

  @Autowired
  private MeasureRepository measureRepository;

  @PersistenceContext
  private EntityManager em;

  //SELECT m FROM Measure m WHERE m.channel.sensor.room.id = :roomId
  @Test
  public void getMeasuresByRoomIdTestExample(){
    //preparar datos...
    Room room = new Room(1L, "sala");
    em.persist(room);

    Sensor sensor = new Sensor(1L, "termostato","temperatura" ,"sansumg", room);
    em.persist(sensor);

    Channel channel = new Channel(1L, "canal1", 80d,-10d, sensor);
    em.persist(channel);

    em.persist(new Measure(1L, 25d, new Date(), channel));
    em.persist(new Measure(2L, 25d, new Date(), channel));

    //prueba..
    List<Measure> measures =measureRepository.getMeasuresByRoomId(1L);

    //verificaciones
    Assertions.assertEquals(2, measures.size());
  }

  @Test
  @Sql({"/testdata/get_measures_by_roomid.sql"})
  public void getMeasuresByRoomIdTest(){
    //prueba..
    List<Measure> measures =measureRepository.getMeasuresByRoomId(1L);

    //verificaciones
    Assertions.assertEquals(4, measures.size());
  }

}
