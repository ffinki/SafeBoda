package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import services.EventSubject;
import services.PromoCodeObserver;

/**
 * Entity implementation class for Entity: PromoCode
 *
 */
@Entity
@NamedQuery(name = "PromoCode.getAll", query = "SELECT p FROM PromoCode p")
@NamedQuery(name = "PromoCode.getActive", query = "SELECT p FROM PromoCode p WHERE p.active = true")
@NamedQuery(name = "PromoCode.getById", query = "SELECT p FROM PromoCode p WHERE p.id = :id")

public class PromoCode implements Serializable, PromoCodeObserver {

	@Transient
	private static final long serialVersionUID = 1L;
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "event_fk")
	private Event event;
	private String code;
	private Integer amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private Boolean active;
	private Double radius;
	//

	public PromoCode() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
		this.event.registerObserver(this);
		this.updateValidity(event);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
		this.updateValidity(this.event);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.updateValidity(this.event);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.updateValidity(this.event);
		if (this.endDate.after(new Date()))
			this.active = false;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}
	
	public boolean updateValidity(EventSubject eventSubject) {
		if (eventSubject == null) return false;
		if (this.getStartDate() == null || this.getEndDate() == null) return false;
		Event event = (Event)eventSubject;
		boolean isActive = this.getStartDate().after(event.getStartDate()) && 
				this.getEndDate().before(event.getEndDate());
		if (amount != null)
			isActive = isActive && this.getAmount() > 0;
		this.setActive(isActive);
		return isActive;
		
	}
	
   
}
