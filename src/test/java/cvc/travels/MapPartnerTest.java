package cvc.travels;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cvc.travels.business.Hotel;
import cvc.travels.business.HotelPartner;
import cvc.travels.business.InputParamCached;
import cvc.travels.business.MapPartner;
import cvc.travels.business.Price;
import cvc.travels.business.PriceDetail;
import cvc.travels.business.Room;
import cvc.travels.business.RoomPartner;
import cvc.travels.business.TreatableException;

public class MapPartnerTest {
	
	
	@Test
	public void mustReturnCalculationPerDay() throws TreatableException {
		List<HotelPartner> listtotals = mockCalculation();
		InputParamCached input = new InputParamCached(
			LocalDate.parse("2021-05-09"),
			LocalDate.parse("2021-05-11"),
			2,
			3
		);
		MapPartner mapPartner = new MapPartner();
		List<Hotel> hotels = mapPartner.mapToThisBusiness(listtotals, input);
		PriceDetail price = hotels.get(0).rooms.get(0).priceDetail;
		
		assertEquals(new BigDecimal("178.75"), price.pricePerDayAdult);
		assertEquals(new BigDecimal("136.70"), price.pricePerDayChild);
	}
	
	@Test
	public void mustReturnTotalCalculation() throws TreatableException {
		List<HotelPartner> listtotals = mockCalculation();
		InputParamCached input = new InputParamCached(
			LocalDate.parse("2021-05-09"),
			LocalDate.parse("2021-05-11"),
			2,
			3
		);
		MapPartner mapPartner = new MapPartner();
		List<Hotel> hotels = mapPartner.mapToThisBusiness(listtotals, input);
		Room room = hotels.get(0).rooms.get(0);
		
		assertEquals(new BigDecimal("2302.80"), room.totalPrice);
	}
	
	
	
	/**
	 * Auxiliares para o mock
	 * @return
	 */

	private List<HotelPartner> mockCalculation() {
		HotelPartner hotelpartner = new HotelPartner();
		hotelpartner.id = 1;
		hotelpartner.name = "Hotel 1";
		hotelpartner.cityCode = 3;
		hotelpartner.cityName = "São Paulo";
		hotelpartner.rooms = roomsCalculation();
		
		List<HotelPartner> hotels = new ArrayList<HotelPartner>();
		hotels.add(hotelpartner);
		
		return hotels;
	}
	
	private List<RoomPartner> roomsCalculation() {
		RoomPartner roompartner = new RoomPartner();
		roompartner.categoryName = "Pousada";
		roompartner.roomID = 1;
		roompartner.price = priceCalculation();
		List<RoomPartner> roomPartnerList = new ArrayList<RoomPartner>();
		roomPartnerList.add(roompartner);
		return roomPartnerList;
	}
	
	private Price priceCalculation() {
		Price price = new Price();
		price.adult = new BigDecimal("125.12");
		price.child = new BigDecimal("95.69");
		return price;
	}
}
