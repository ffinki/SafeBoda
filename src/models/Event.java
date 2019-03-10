package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import services.EventSubject;
import services.Observer;
import services.PromoCodeObserver;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable, EventSubject {

	@Transient
	private static final long serialVersionUID = 1L;
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "location_fk")
	private Location location;
	@OneToMany(mappedBy = "event")
	private List<PromoCode> promoCodes;
	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	//
	@Transient
	private List<Observer> observers;
	
	public Event() {
		super();
		this.observers = new ArrayList<Observer>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		for (Observer o : this.observers) {
			PromoCodeObserver pco = (PromoCodeObserver)o;
			pco.updateValidity(this);
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		for (Observer o : this.observers) {
			PromoCodeObserver pco = (PromoCodeObserver)o;
			pco.updateValidity(this);
		}
	}
	
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public List<Observer> getObservers() {
		return this.observers;
	}
	
   
}
