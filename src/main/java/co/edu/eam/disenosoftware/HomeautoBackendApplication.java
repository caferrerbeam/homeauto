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
public class HomeautoBackendApplication {

  /**
   * Main method.
   *
   * @param args args.
   */
  public static void main(String[] args) {
    SpringApplication.run(HomeautoBackendApplication.class, args);
  }





}
