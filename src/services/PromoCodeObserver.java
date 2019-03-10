package services;

public interface PromoCodeObserver extends Observer{
	
	public boolean updateValidity(EventSubject subject);

}
