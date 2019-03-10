package tests;


import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import models.Event;
import models.Location;
import models.PromoCode;
import services.PromoCodeService;

public class PromoCodesTest {
	
	List<PromoCode> promoCodes;
	PromoCodeService promoCodeService;

	@Before
	public void setUp() throws Exception {
		promoCodes = TestMocks.genereateMockPromoCodes();
		promoCodeService = new PromoCodeService();
		promoCodeService.entityManager = Mockito.mock(EntityManager.class);
	}

	
	@Test
	public void shouldGetAListOfAllThePromoCodes() {
		TypedQuery<PromoCode> mockedQuery = Mockito.mock(TypedQuery.class);
		Mockito.when(mockedQuery.getResultList()).thenReturn(promoCodes);
		Mockito.when(mockedQuery.setParameter(
				Matchers.anyString(), Matchers.anyObject()))
				.thenReturn(mockedQuery);
		Mockito.when(promoCodeService.entityManager.createNamedQuery("PromoCode.getAll", PromoCode.class))
		.thenReturn(mockedQuery);
		promoCodes = promoCodeService.getPromoCodes();
		assertThat(promoCodes.size(), is(3));
		
	}
	
	@Test
	public void shouldGetAListOfActivePromoCodes() {
		Event e = promoCodes.get(0).getEvent();
		Calendar c = Calendar.getInstance();
		c.setTime(e.getStartDate());
		c.add(Calendar.DAY_OF_MONTH, 21);
		e.setEndDate(c.getTime());
		TypedQuery<PromoCode> mockedQuery = Mockito.mock(TypedQuery.class);
		Mockito.when(mockedQuery.getResultList()).thenReturn(promoCodes);
		Mockito.when(mockedQuery.setParameter(
				Matchers.anyString(), Matchers.anyObject()))
				.thenReturn(mockedQuery);
		Mockito.when(promoCodeService.entityManager.createNamedQuery("PromoCode.getActive", PromoCode.class))
		.thenReturn(mockedQuery);
		promoCodes = promoCodeService.getActivePromoCodes();
		assertThat(promoCodes.get(2).getActive(), is(false));
		
	}
	
	@Test
	public void promoCodeFarFromTheEventIsNotValid() {
		PromoCode p = promoCodes.get(0);
		Location lloretDeMar = new Location();
		lloretDeMar.setName("Lloret de Mar");
		lloretDeMar.setLatitude(41.702675);
		lloretDeMar.setLongitude(2.8218118);
		Location illaFantasia = new Location();
		illaFantasia.setName("Parc del Montnegre");
		illaFantasia.setLatitude(41.591501);
		illaFantasia.setLongitude(2.4284082);
		boolean isValid = promoCodeService.isPromoCodeValid(lloretDeMar.getLatitude(), lloretDeMar.getLongitude(),
				illaFantasia.getLatitude(), illaFantasia.getLongitude(), p);
		assertThat(isValid, is(false));
	}
	
	@Test
	public void promoCodeInRadiusOfTheEventIsValid() {
		PromoCode p = promoCodes.get(1);
		Location airport = new Location();
		airport.setName("Barcelona el Prat");
		airport.setLatitude(41.3357673);
		airport.setLongitude(1.9436364);
		Location pobleEspanyol = new Location();
		pobleEspanyol.setName("Poble Espanyol");
		pobleEspanyol.setLatitude(41.3777973);
		pobleEspanyol.setLongitude(2.1204405);
		boolean isValid = promoCodeService.isPromoCodeValid(airport.getLatitude(), airport.getLongitude(), 
				pobleEspanyol.getLatitude(), pobleEspanyol.getLongitude(), p);
		assertThat(isValid, is(true));
	}

}
