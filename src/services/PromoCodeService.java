package services;

import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import helpers.DistanceCalculator;
import models.Location;
import models.PromoCode;

/**
 * Session Bean implementation class PromoCodeService
 */
@Stateless
@LocalBean
public class PromoCodeService {
	
	@PersistenceUnit(unitName = "safeboda")
	public EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public PromoCodeService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPromoCode(PromoCode pc) {
    	Random r = new Random();
    	int promoCodeSerial = 1000000000 + r.nextInt(899999999);
    	pc.setCode("BCN" + promoCodeSerial + "SB");
    	entityManager.persist(pc);
    }
    
    public List<PromoCode> getPromoCodes() {
    	TypedQuery<PromoCode> query = entityManager
    			.createNamedQuery("PromoCode.getAll", PromoCode.class);
    	List<PromoCode> result = query.getResultList();
    	return result;
    }
    
    public List<PromoCode> getActivePromoCodes() {
    	TypedQuery<PromoCode> query = entityManager
    			.createNamedQuery("PromoCode.getActive", PromoCode.class);
    	List<PromoCode> result = query.getResultList();
    	return result;
    }
    
    public PromoCode getPromoCodeById(int id) {
    	TypedQuery<PromoCode> query = entityManager
    			.createNamedQuery("PromoCode.getById", PromoCode.class);
    	query.setParameter("id", id);
    	PromoCode result = query.getSingleResult();
    	return result;
    }
    
    public boolean isPromoCodeValid(double latOrigin, double longOrigin,
    		double latDest, double longDest, PromoCode promoCode) {
    	Location promoCodeLoc = promoCode.getEvent().getLocation();
    	double distanceToOrigin = DistanceCalculator.distanceInKmBetweenEarthCoordinates(latOrigin, longOrigin,
    			promoCodeLoc.getLatitude(), promoCodeLoc.getLongitude());
    	double distanceToDestination = DistanceCalculator.distanceInKmBetweenEarthCoordinates(latDest, longDest,
    			promoCodeLoc.getLatitude(), promoCodeLoc.getLongitude());
    	boolean valid = distanceToOrigin <= promoCode.getRadius();
    	valid = valid || distanceToDestination <= promoCode.getRadius();
    	valid = valid && promoCode.getAmount() > 0;
    	return valid;
    }

}
