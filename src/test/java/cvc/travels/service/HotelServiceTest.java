package cvc.travels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cvc.travels.business.ErrorCodes;
import cvc.travels.business.InputParameters;
import cvc.travels.business.TreatableException;

public class HotelServiceTest {

	@Test
	public void mustBeInvalidWhenCheckinAfterCheckoutOnHotelsByCity() throws Exception {
		InputParameters params = new InputParameters(
			"2020-05-15",
			"2020-05-10",
			2,
			3
		);
		HotelService hotelservice = new HotelService();
		try {
			hotelservice.hotelsByCity(1, params);
		} catch (TreatableException e) {
			assertEquals(ErrorCodes.CHECKIN_CHECKOUT_RANGE_INVALID, e.getErrorCode());
			return;
		}
		throw new Exception("The exception was not thrown.");
	}
	
	@Test
	public void mustBeInvalidWhenCheckinAfterCheckoutOnHotelByHotelId() throws Exception {
		InputParameters params = new InputParameters(
			"2020-05-13",
			"2020-05-10",
			2,
			3
		);
		HotelService hotelservice = new HotelService();
		try {
			hotelservice.hotel(1, params);
		} catch (TreatableException e) {
			assertEquals(ErrorCodes.CHECKIN_CHECKOUT_RANGE_INVALID, e.getErrorCode());
			return;
		}
		throw new Exception("The exception was not thrown.");
	}
}
