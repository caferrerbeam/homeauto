package co.edu.eam.disenosoftware.homeauto.services;

import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Measure;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.repositories.ChannelRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.MeasureRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {

  @Autowired
  private SensorRepository sensorRepository;

  @Autowired
  private ChannelRepository channelRepository;

  @Autowired
  private MeasureRepository measureRepository;

  public void createMeasure(Long sensorId, Long channelId, double value) {

    Sensor sensor = sensorRepository.find(sensorId);

    if (sensor == null) {
      throw new RuntimeException("Sensor no existe");
    }

    Channel channel = channelRepository.find(channelId);

    if (channel == null) {
      throw new RuntimeException("channel no existe");
    }

    if (channel.getSensor().getId() != sensorId) {
      throw new RuntimeException("channel no pertenece al sensor");
    }

    if (value > channel.getMax()) {
      value = channel.getMax();
    }

    value = value < channel.getMin() ? channel.getMin() : value;

    Measure measure = new Measure(value, channel);
    measureRepository.create(measure);
  }
}
