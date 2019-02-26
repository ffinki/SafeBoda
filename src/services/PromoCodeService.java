package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import models.PromoCode;

/**
 * Session Bean implementation class PromoCodeService
 */
@Stateless
@LocalBean
public class PromoCodeService {
	
	@PersistenceUnit(unitName = "safeboda")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public PromoCodeService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPromoCode(PromoCode pc) {
    	entityManager.persist(pc);
    }
    
    public List<PromoCode> getPromoCodes() {
    	TypedQuery<PromoCode> query = entityManager
    			.createQuery("SELECT * FROM PromoCode", PromoCode.class);
    	List<PromoCode> result = query.getResultList();
    	return result;
    }

}
