package co.edu.eam.disenosoftware;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runner class.
 */
@SpringBootApplication
public class HomeautoBackendApplication implements CommandLineRunner {

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

  }
}
