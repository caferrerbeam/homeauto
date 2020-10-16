package co.edu.eam.disenosoftware.controllers;

import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.model.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SensorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @PersistenceContext
  private EntityManager em;

  @Test
  @Sql({"/testdata/get_measures_by_roomid.sql"})
  public void testGetAllSensor() throws Exception {
    //1. crear la peticion
    RequestBuilder request = MockMvcRequestBuilders.get("/sensors");
    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();
    //4. hacer las asersion
    Assertions.assertEquals(200, status);

    Sensor[] sensors = objectMapper.readValue(body, Sensor[].class);
    Assertions.assertEquals(2, sensors.length);
  }

  @Test
  @Sql({"/testdata/testGetAllSensorByName.sql"})
  public void testGetAllSensorByName() throws Exception {
    //1. crear la peticion
    RequestBuilder request = MockMvcRequestBuilders.get("/sensors/by-name?name_sensor=sensor1");
    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();
    //4. hacer las asersion
    Assertions.assertEquals(200, status);

    Sensor[] sensors = objectMapper.readValue(body, Sensor[].class);
    Assertions.assertEquals(2, sensors.length);
  }

  @Test
  @Sql({"/testdata/testGetSensorById.sql"})
  public void testGetSensorById() throws Exception {
    //1. crear la peticion
    RequestBuilder request = MockMvcRequestBuilders.get("/sensors/1");
    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();
    //4. hacer las asersion
    Assertions.assertEquals(200, status);

    Sensor sensors = objectMapper.readValue(body, Sensor.class);
    Assertions.assertEquals("sensor1", sensors.getName());
    Assertions.assertEquals("marca", sensors.getBrand());
    Assertions.assertEquals("tipo", sensors.getType());
    Assertions.assertEquals(2, sensors.getChannels().size());
  }


  @Test
  @Sql({"/testdata/testCreateSensorOk.sql"})
  public void testCreateSensorOK() throws Exception {
    //1. crear la peticion
    String jsonBody = "{\n" +
            "    \"name\":\"termostato\",\n" +
            "    \"type\": \"temp\",\n" +
            "    \"brand\": \"gato\",\n" +
            "    \"roomId\":1,\n" +
            "    \"min\": 10.3,\n" +
            "    \"max\": 100\n" +
            "}";
    RequestBuilder request = MockMvcRequestBuilders.post("/sensors")
            .contentType("application/json")
            .content(jsonBody);

    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();

    //4. hacer las asersion
    Assertions.assertEquals(200, status);

    List<Sensor> sensors = em.createQuery("select s from Sensor s where s.name = 'termostato'").getResultList();
    Sensor sensor = sensors.get(0);

    Assertions.assertNotNull(sensor);
    Assertions.assertEquals("temp", sensor.getType());
    Assertions.assertEquals("gato", sensor.getBrand());

    List<Channel> channels = em.createQuery("select c from Channel c where c.sensor.id = :sensorId")
            .setParameter("sensorId", sensor.getId())
            .getResultList();
    Channel channel = channels.get(0);

    Assertions.assertNotNull(channel);
    Assertions.assertEquals("channel1",channel.getName());
    Assertions.assertEquals(10.3,channel.getMin());
    Assertions.assertEquals(100,channel.getMax());
  }

  @Test
  public void testCreateSensorNotExistRoom() throws Exception {
    //1. crear la peticion
    String jsonBody = "{\n" +
            "    \"name\":\"termostato\",\n" +
            "    \"type\": \"temp\",\n" +
            "    \"brand\": \"gato\",\n" +
            "    \"roomId\":100,\n" +
            "    \"min\": 10.3,\n" +
            "    \"max\": 100\n" +
            "}";
    RequestBuilder request = MockMvcRequestBuilders.post("/sensors")
            .contentType("application/json")
            .content(jsonBody);

    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();

    //4. hacer las asersion
    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), status);
    System.out.println(body);

    ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
    Assertions.assertEquals("0001", error.getCode());
  }

  @Test
  @Sql({"/testdata/testCreateSensorMinGreaterThanMax.sql"})
  public void testCreateSensorMinGreaterThanMax() throws Exception {
    //1. crear la peticion
    String jsonBody = "{\n" +
            "    \"name\":\"termostato\",\n" +
            "    \"type\": \"temp\",\n" +
            "    \"brand\": \"gato\",\n" +
            "    \"roomId\":1,\n" +
            "    \"min\": 1000.3,\n" +
            "    \"max\": 100\n" +
            "}";
    RequestBuilder request = MockMvcRequestBuilders.post("/sensors")
            .contentType("application/json")
            .content(jsonBody);

    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();

    //4. hacer las asersion
    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    System.out.println(body);

    ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
    Assertions.assertEquals("0003", error.getCode());
  }

  @Test
  @Sql({"/testdata/testCreateSensorMinGreaterThanMax.sql"})
  public void testCreateSensorBadParameters() throws Exception {
    //1. crear la peticion
    String jsonBody = "{\n" +
            "    \"type\": \"temp\",\n" +
            "    \"brand\": \"gato\",\n" +
            "    \"roomId\":1,\n" +
            "    \"min\": 1000.3,\n" +
            "    \"max\": 100\n" +
            "}";
    RequestBuilder request = MockMvcRequestBuilders.post("/sensors")
            .contentType("application/json")
            .content(jsonBody);

    //2. hacer la peticion
    ResultActions result = mockMvc.perform(request);

    //3. sacar los resultados de la peticion
    String body = result.andReturn().getResponse().getContentAsString();
    int status = result.andReturn().getResponse().getStatus();

    //4. hacer las asersion
    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    System.out.println(body);

    ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);
    Assertions.assertEquals("1000", error.getCode());
  }

}
