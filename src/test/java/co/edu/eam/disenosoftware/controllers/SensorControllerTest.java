package co.edu.eam.disenosoftware.controllers;

import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.model.requests.CreateSensorRequest;
import co.edu.eam.disenosoftware.homeauto.model.responses.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class SensorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @Sql({"/testdata/controllers/sensors/get_all_sensors.sql"})
  public void testGetAllSensor() throws Exception {

    RequestBuilder request = get("/sensors");

    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().isOk());

    MvcResult response = result.andReturn();

    String json = response.getResponse().getContentAsString();
    System.out.println(json);

    Sensor[] sensors = objectMapper.readValue(json, Sensor[].class);

    Assertions.assertEquals(2, sensors.length);
  }

  @Test
  @Sql({"/testdata/controllers/sensors/create_sensor.sql"})
  public void createSensorOk() throws Exception {

    CreateSensorRequest body = new CreateSensorRequest("sensor", "type", "brand",1L, 10.0, 100.0);
    String jsonBody = objectMapper.writeValueAsString(body);
    RequestBuilder request = post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody);

    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().isOk());

    String jsonResponse = result.andReturn().getResponse().getContentAsString();
    Sensor sensor = objectMapper.readValue(jsonResponse, Sensor.class);

    Assertions.assertEquals(1, sensor.getChannels().size());

  }

  @Test
  @Sql({"/testdata/controllers/sensors/create_sensor.sql"})
  public void createSensorBadParams() throws Exception {

    CreateSensorRequest body = new CreateSensorRequest(null, "type", "brand",1L, 10.0, 100.0);
    String jsonBody = objectMapper.writeValueAsString(body);
    RequestBuilder request = post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody);

    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  @Sql({"/testdata/controllers/sensors/create_sensor.sql"})
  public void createSensorBadParamsMinMax() throws Exception {

    CreateSensorRequest body = new CreateSensorRequest("nombre", "type", "brand",1L, 1000.0, 100.0);
    String jsonBody = objectMapper.writeValueAsString(body);
    RequestBuilder request = post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody);

    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(400));

    String jsonResponse = result.andReturn().getResponse().getContentAsString();
    ErrorResponse error = objectMapper.readValue(jsonResponse, ErrorResponse.class);
    Assertions.assertEquals("el max debe ser menor que el min", error.getMessage());
  }

  @Test
  public void createSensorNotFoundRoom() throws Exception {
    CreateSensorRequest body = new CreateSensorRequest("name", "type", "brand",1L, 100.0, 100.0);
    String jsonBody = objectMapper.writeValueAsString(body);
    RequestBuilder request = post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody);

    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(404));

    String jsonResponse = result.andReturn().getResponse().getContentAsString();
    ErrorResponse error = objectMapper.readValue(jsonResponse, ErrorResponse.class);
    Assertions.assertEquals("0001", error.getCode());
  }
}
