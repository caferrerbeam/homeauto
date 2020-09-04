package co.edu.eam.disenosoftware.homeauto.repositories;

import co.edu.eam.disenosoftware.homeauto.model.entities.Room;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Room repository
 */
@Component // para definir que esta es una clase que springboot va a instanciar..
@Transactional
public class RoomRepository {

  /**
   * Entity manager
   */
  @PersistenceContext
  private EntityManager em;

  /**
   * Create a room
   *
   * @param room ,room to Create.
   */
  public void create(Room room) {
    em.persist(room);
  }

  /**
   * Find a room by primary key
   *
   * @param id primary key
   * @return a room or null if not exists
   */
  public Room find(Long id) {
    return em.find(Room.class, id);
  }

  /**
   * Edit a room
   *
   * @param room room to edit
   */
  public void edit(Room room) {
    em.merge(room);
  }

  /**
   * Delete a room
   *
   * @param id primary key
   * @return room deleted or null if not exists
   */
  public Room delete(Long id) {

    Room room = find(id);

    if (room != null) {
      em.remove(room);
    }

    return room;
  }

  public List<Room> getAllRooms() {
    String queryStr = "SELECT r FROM Room r";

    Query query = em.createQuery(queryStr);

    return query.getResultList();
  }

}
