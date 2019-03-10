package tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import models.Event;
import models.Location;
import models.PromoCode;

public class TestMocks {
	
	private static TestMocks testMock;
	
	public static List<PromoCode> genereateMockPromoCodes() {
		if (testMock == null) testMock = new TestMocks();
		Location loc = new Location();
		loc.setName("Camp Nou, Barcelona");
		loc.setDescription("The Camp Nou stadium in Barcelona");
		loc.setLatitude(41.3809);
		loc.setLongitude(2.122920000000022);
		//
		List<Event> events = new ArrayList<Event>();
		Event campNou = new Event();
		campNou.setName("Camp Nou open day");
		campNou.setDescription("A open day for the tourists to visit Camp Nou");
		campNou.setLocation(loc);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		campNou.setStartDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		campNou.setEndDate(calendar.getTime());
		//
		events.add(campNou);
		//
		Random r = new Random();
		List<PromoCode> promoCodes = new ArrayList<PromoCode>();
		PromoCode pc1 = new PromoCode();
		int rndCode = 1000000000 + r.nextInt(899999999);
		pc1.setCode("BCN" + rndCode + "NC");
		pc1.setEvent(campNou);
		calendar.setTime(new Date());
		pc1.setStartDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		pc1.setEndDate(calendar.getTime());
		//pc1.setActive(true);
		pc1.setAmount(4);
		pc1.setRadius(20.00); //the radius is expressed in kilometers
		//
		PromoCode pc2 = new PromoCode();
		rndCode = 1000000000 + r.nextInt(899999999);
		pc2.setCode("BCN" + rndCode + "NC");
		pc2.setEvent(campNou);
		pc2.setStartDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		pc2.setEndDate(calendar.getTime());
		//pc2.setActive(true);
		pc2.setAmount(3);
		pc2.setRadius(5.00);
		//
		PromoCode pc3 = new PromoCode();
		rndCode = 1000000000 + r.nextInt(899999999);
		pc3.setCode("BCN" + rndCode + "NC");
		pc3.setEvent(campNou);
		pc3.setStartDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		pc3.setEndDate(calendar.getTime());
		//pc3.setActive(true);
		pc3.setAmount(6);
		pc2.setRadius(25.00);
		//
		promoCodes.add(pc1);
		promoCodes.add(pc2);
		promoCodes.add(pc3);
		return promoCodes;
	}

}
