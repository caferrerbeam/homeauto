package co.edu.eam.disenosoftware.homeautobackend.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class RoomRepositoryTest {

  @Autowired
  private RoomRepository roomRepository;

  @PersistenceContext
  private EntityManager em;


  @BeforeEach
  public void setup() {
    em.createQuery("delete from Room");
  }

  @Test
  public void testCreate() {
    Room room = new Room(1L, "room");
    roomRepository.create(room);

    Room roomToAssert = roomRepository.find(1L);
    Assertions.assertEquals("room", roomToAssert.getName());
  }

  @Test
  public void testFind() {
    Room room = new Room(1L, "room");
    roomRepository.create(room);

    Room roomToAssert = roomRepository.find(1L);
    Assertions.assertEquals("room", roomToAssert.getName());
  }

  @Test
  public void testUpdate() {
    Room room = new Room(1L, "room");
    roomRepository.create(room);

    room.setName("name_updated");
    roomRepository.edit(room);

    Room roomToAssert = roomRepository.find(1L);
    Assertions.assertEquals("name_updated", roomToAssert.getName());
  }

  @Test
  public void testDelete() {
    Room room = new Room(1L, "room");
    roomRepository.create(room);

    roomRepository.delete(1L);

    Room roomToAssert = roomRepository.find(1L);
    Assertions.assertNull(roomToAssert);
  }

  @Sql({"/testdata/room_get_all.sql"})
  @Test
  public void testGetAll(){
    List<Room> roomsr = roomRepository.getAllRooms();
    Assertions.assertEquals(3, roomsr.size());
  }
}
