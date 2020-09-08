package co.edu.eam.disenosoftware.homeautobackend.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@SpringBootTest
public class RoomRepositoryTest {

  @Autowired
  private RoomRepository roomRepository;

  @PersistenceContext
  private EntityManager em;

  @Test
  public void createNotExistingRoomTest() {
    //Codigo que se esta probando...
    Room room = new Room(1L, "sala");
    roomRepository.create(room);

    //codigo de la prueba.......
    Room roomToAssert = roomRepository.find(1L);

    Assertions.assertNotNull(roomToAssert);
    Assertions.assertEquals("sala", roomToAssert.getName());
  }

  @Test
  public void getAllRoomsTest() {
    //codigo de preparacion de la preuba
    roomRepository.create(new Room(1L, "room1"));
    roomRepository.create(new Room(2L, "room2"));

    //codigo que estoy prodando..
    List<Room> roomListToAssert = roomRepository.getAllRooms();

    //verificaciones
    Assertions.assertEquals(1, roomListToAssert.size());
  }

  @Test
  public void deleteExistingRoomTest() {
    //codigo de la preparacion
    roomRepository.create(new Room(1L, "room1"));

    //codigo que se esta probando
    Room deletedRoom = roomRepository.delete(1L);

    //verificaciones
    Assertions.assertNotNull(deletedRoom);
    Assertions.assertEquals("room1", deletedRoom.getName());

    Room roomToAssert = em.find(Room.class, 1L);
    Assertions.assertNull(roomToAssert);
  }

  @Test
  public void deleteNotExistingRoomTest(){
    //codigo que se esta probando
    Room deletedRoom = roomRepository.delete(1L);
    Assertions.assertNull(deletedRoom);
  }

  @Test
  public void findExistingRoomTest() {
    //Codigo que se esta probando...
    Room room = new Room(1L, "sala");
    em.persist(room);

    //codigo de la prueba.......
    Room roomToAssert = roomRepository.find(1L);

    Assertions.assertNotNull(roomToAssert);
    Assertions.assertEquals("sala", roomToAssert.getName());
  }

  @Test
  public void updateRoomTest(){
    //codigo de preparacion
    Room room = new Room(1L, "room1");
    roomRepository.create(room);

    //prueba
    room.setName("sala");
    roomRepository.edit(room);

    //verficaciones
    Room roomToAssert = roomRepository.find(1L);
    Assertions.assertEquals("sala", roomToAssert.getName());
  }

}
