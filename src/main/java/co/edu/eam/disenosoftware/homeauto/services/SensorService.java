package co.edu.eam.disenosoftware.homeauto.services;

import co.edu.eam.disenosoftware.homeauto.exceptions.BusinessException;
import co.edu.eam.disenosoftware.homeauto.exceptions.ErrorCodesEnum;
import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import co.edu.eam.disenosoftware.homeauto.repositories.ChannelRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.RoomRepository;
import co.edu.eam.disenosoftware.homeauto.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private SensorRepository sensorRepository;

  @Autowired
  private ChannelRepository channelRepository;

  public void create(String name, String type,String brand, Long roomId, Double min, Double max){

    Room room = roomRepository.find(roomId);

    if(room==null) {
      throw new BusinessException("No se puede crear el sensor porq eel cuarto no existe", ErrorCodesEnum.ROOM_NOT_FOUND);
    }

    Sensor sensor = new Sensor(name,type, brand, room);
    Channel channel = new Channel("channel1", max, min);
    channel.setSensor(sensor);

    sensor.getChannels().add(channel);
    sensorRepository.create(sensor);
  }

  public void create(String name, String type, String brand, Long roomId, List<Double[]> ranges) {
    Room room = roomRepository.find(roomId);

    if(room==null) {
      throw new BusinessException("no existe el room", ErrorCodesEnum.ROOM_NOT_FOUND);
    }

    if (ranges.isEmpty()) {
      throw new BusinessException("lista de canales vacia", ErrorCodesEnum.CHANNEL_LIST_EMPTY);
    }

    Sensor sensor = new Sensor(name,type, brand, room);

    int i = 0;
    for (Double[] range : ranges) {
      Channel channel = new Channel("channel" + i, range[1], range[0]);
      channel.setSensor(sensor);
      sensor.getChannels().add(channel);
      i++;
    }

    sensorRepository.create(sensor);
  }
}
