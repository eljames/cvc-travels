package cvc.travels.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cvc.travels.business.ErrorCodes;
import cvc.travels.business.Hotel;
import cvc.travels.business.HotelPartner;
import cvc.travels.business.InputParamCached;
import cvc.travels.business.InputParameters;
import cvc.travels.business.MapPartner;
import cvc.travels.business.TreatableException;

@Component
public class HotelService {
	
	@Autowired
	private HotelPartnerService hotelPartner;
	
	/**
	 * Gets all hotels by a cityId
	 * @param cityId The id of a specific city
	 * @return
	 * @throws TreatableException 
	 */
	
	public List<Hotel> hotelsByCity(int cityId, InputParameters inputs) throws TreatableException {
		InputParamCached cached = createInputParamCached(inputs);
		List<HotelPartner> hotels = this.hotelPartner.hotelsByCity(cityId);
		return new MapPartner().mapToThisBusiness(hotels, cached);
	}

	/**
	 * Gets a hotel, specifying a hotelId.
	 * @param hotelId
	 * @return
	 * @throws TreatableException 
	 */
	public List<Hotel> hotel(int hotelId, InputParameters inputs) throws TreatableException {
		InputParamCached cached = createInputParamCached(inputs);
		List<HotelPartner> hotels = this.hotelPartner.hotel(hotelId);
		return new MapPartner().mapToThisBusiness(hotels, cached);
	}
	
	private InputParamCached createInputParamCached(InputParameters inputs) throws TreatableException {
		LocalDate checkInDate = inputs.checkinInDate();
		LocalDate checkOutDate = inputs.checkinOutDate();
		throwsExceptionIfOutOfRange(checkInDate, checkOutDate);
		InputParamCached cached = new InputParamCached(
			checkInDate,
			checkOutDate,
			inputs.getNumberOfAdults(),
			inputs.getNumberOfChildren()
		);
		return cached;
	}
	
	private boolean isInvalidDateRange(LocalDate checkin, LocalDate checkout) {
		long daysInterval = ChronoUnit.DAYS.between(checkin, checkout);
		if(daysInterval < 0) {
			return true;
		}
		return false;
	}
	
	private void throwsExceptionIfOutOfRange(LocalDate checkInDate, LocalDate checkOutDate) throws TreatableException {
		if(isInvalidDateRange(checkInDate, checkOutDate)) {
			throw new TreatableException(
				ErrorCodes.CHECKIN_CHECKOUT_RANGE_INVALID,
				"The checkout date must not be before checkin date"
			);
		}
	}
}
