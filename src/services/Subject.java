package services;

import java.util.List;

public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public List<Observer> getObservers();

}
