package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Location;

/**
 * Session Bean implementation class LocationService
 */
@Stateless
@LocalBean
public class LocationService {
	
	@PersistenceContext(unitName = "safeboda")
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public LocationService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addLocation(Location l) {
    	entityManager.persist(l);
    	
    }

}
