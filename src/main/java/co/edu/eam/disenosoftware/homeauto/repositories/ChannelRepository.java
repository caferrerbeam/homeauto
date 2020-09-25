package co.edu.eam.disenosoftware.homeauto.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Channel;
import co.edu.eam.disenosoftware.homeauto.model.entities.Sensor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class ChannelRepository {

  /**
   * Entity manager
   */
  @PersistenceContext
  private EntityManager em;


  public void create(Channel channel) {
    em.persist(channel);
  }


  public Channel find(Long id) {
    return em.find(Channel.class, id);
  }
}
