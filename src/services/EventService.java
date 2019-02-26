package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Event;

/**
 * Session Bean implementation class EventService
 */
@Stateless
@LocalBean
public class EventService {
	
	@PersistenceContext(unitName = "safeboda")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public EventService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addEvent(Event e) {
    	entityManager.persist(e);
    }

}
