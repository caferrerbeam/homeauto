package co.edu.eam.disenosoftware.homeauto.services;

import co.edu.eam.disenosoftware.homeauto.exceptions.BusinessException;
import co.edu.eam.disenosoftware.homeauto.exceptions.ErrorCodesEnum;
import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class SensorServiceTest {

  @Autowired
  private SensorService sensorService;


  @PersistenceContext
  private EntityManager em;

  @Test
  public void createTest(){
    //datos de preparacion
    Room room = new Room("room");
    em.persist(room);

    Long roomId = room.getId();

    //la prueba
    sensorService.create("sensor","temperatura", "panasonic",roomId, -35.0, 80.0 );

    //las verificaciones
    List<Sensor> sensors = em.createQuery("select s from Sensor s where s.name = 'sensor'").getResultList();
    Sensor sensor = sensors.get(0);

    Assertions.assertNotNull(sensor);
    Assertions.assertEquals("temperatura", sensor.getType());
    Assertions.assertEquals("panasonic", sensor.getBrand());

    List<Channel> channels = em.createQuery("select c from Channel c where c.sensor.id = :sensorId")
            .setParameter("sensorId", sensor.getId())
            .getResultList();
    Channel channel = channels.get(0);

    Assertions.assertNotNull(channel);
    Assertions.assertEquals("channel1",channel.getName());
    Assertions.assertEquals(-35.0,channel.getMin());
    Assertions.assertEquals(80.0,channel.getMax());
  }

  @Test
  public void createWithMultipleChannelsTest(){
    //datos de preparacion
    Room room = new Room("room");
    em.persist(room);

    Long roomId = room.getId();

    //la prueba

    List<Double[]> ranges = new ArrayList<>();
    ranges.add(new Double[]{ -10.0,50.0 });
    ranges.add(new Double[]{ -100.0,500.0 });

    sensorService.create("sensor","temperatura", "panasonic",roomId, ranges);

    //las verificaciones
    List<Sensor> sensors = em.createQuery("select s from Sensor s where s.name = 'sensor'").getResultList();
    Sensor sensor = sensors.get(0);

    Assertions.assertNotNull(sensor);
    Assertions.assertEquals("temperatura", sensor.getType());
    Assertions.assertEquals("panasonic", sensor.getBrand());

    List<Channel> channels = em.createQuery("select c from Channel c where c.sensor.id = :sensorId")
            .setParameter("sensorId", sensor.getId())
            .getResultList();

    Assertions.assertEquals(2, channels.size());

    Assertions.assertEquals("channel0",channels.get(0).getName());
    Assertions.assertEquals(-10.0,channels.get(0).getMin());
    Assertions.assertEquals(50.0,channels.get(0).getMax());

    Assertions.assertEquals("channel1",channels.get(1).getName());
    Assertions.assertEquals(-100.0,channels.get(1).getMin());
    Assertions.assertEquals(500.0,channels.get(1).getMax());
  }

  @Test
  public void createWithoutRoomExample1() {
    try {
      sensorService.create("sensor", "temperatura", "panasonic", 1L, 0.0, 10.0);
    }catch (Exception exc) {

      if (!(exc instanceof RuntimeException)){
        Assertions.fail();
      }

      Assertions.assertEquals("no existe el room", exc.getMessage());
    }
  }

  @Test
  public void createWithoutRoomExample2() {
    BusinessException exception = Assertions.assertThrows(BusinessException.class,
            () -> sensorService.create("sensor",
                    "temperatura", "panasonic",
                    1L, 0.0, 10.0)
    );

    Assertions.assertEquals(ErrorCodesEnum.ROOM_NOT_FOUND, exception.getCodigo());
  }



}
