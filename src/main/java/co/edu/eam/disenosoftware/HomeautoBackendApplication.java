package co.edu.eam.disenosoftware;

import co.edu.eam.disenosoftware.homeauto.model.entities.Measure;
import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.repositories.MeasureRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Runner class.
 */
@SpringBootApplication
public class HomeautoBackendApplication implements CommandLineRunner {

  // Inyeccion de dependencia
  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private MeasureRepository measureRepository;

  /**
   * Main method.
   *
   * @param args args.
   */
  public static void main(String[] args) {
    SpringApplication.run(HomeautoBackendApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    /*Room room = new Room(2L, "Cocina");
    roomRepository.create(room);

    Room room2 = roomRepository.find(2L);
    System.out.println(room2);

    room2.setName("Patio");
    roomRepository.edit(room2);

    Room room3 = roomRepository.find(2L);
    System.out.println(room3);

    roomRepository.delete(2L);

    Room room4 = roomRepository.find(2L);
    System.out.println(room4);*/

    /*List<Room> rooms = roomRepository.getAllRooms();
    for (Room r : rooms) {
      System.out.println(r);
    }


    List<Measure> measures = measureRepository.getMeasureValueGreaterThan(12);
    for (Measure m : measures) {
      System.out.println(m);
    }*/

    System.out.println(" ------------MEDIDASPOR CUARTO--------------------------------");
    List<Measure> measuresByRoom = measureRepository.getMeasuresByRoomId(1L);
    for (Measure m : measuresByRoom) {
      System.out.println(m);
    }

    System.exit(0);
  }
}
