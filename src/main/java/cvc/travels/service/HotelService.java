package cvc.travels.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.json.JsonMapper;

import cvc.travels.business.ErrorCodes;
import cvc.travels.business.Hotel;
import cvc.travels.business.HotelPartner;
import cvc.travels.business.InputParamCached;
import cvc.travels.business.InputParameters;
import cvc.travels.business.MapPartner;
import cvc.travels.business.TreatableException;

@Component
public class HotelService {
	
	private static final Logger logger = LogManager.getLogger(HotelService.class);
	
	@Autowired
	private HotelPartnerService hotelPartner;
	
	/**
	 * Gets all hotels by a cityId
	 * @param cityId The id of a specific city
	 * @return
	 * @throws TreatableException 
	 * @throws Exception 
	 */
	
	public List<Hotel> hotelsByCity(int cityId, InputParameters inputs) throws TreatableException, Exception {
		try {
			logger.info("Input hotelsByCity. cityId: " + cityId + "; parameters: " + inputs.toString());
			InputParamCached cached = createInputParamCached(inputs);
			List<HotelPartner> hotelspartner = this.hotelPartner.hotelsByCity(cityId);
			List<Hotel> hotels = new MapPartner().mapToThisBusiness(hotelspartner, cached);
			logger.info("response hotels: " + new JsonMapper().writeValueAsString(hotels));
			return hotels;
		} catch (TreatableException e) {
			logger.error(e.toString());
			throw e;
		}
	}

	/**
	 * Gets a hotel, specifying a hotelId.
	 * @param hotelId
	 * @return
	 * @throws TreatableException 
	 * @throws Exception 
	 */
	public List<Hotel> hotel(int hotelId, InputParameters inputs) throws TreatableException, Exception {
		try {
			logger.info("Input hotelsByCity. cityId: " + hotelId + "; parameters: " + inputs.toString());
			InputParamCached cached = createInputParamCached(inputs);
			List<HotelPartner> hotelspartner = this.hotelPartner.hotel(hotelId);
			List<Hotel> hotels = new MapPartner().mapToThisBusiness(hotelspartner, cached);
			logger.info("response hotels: " + new JsonMapper().writeValueAsString(hotels));
			return hotels;
		} catch (TreatableException e) {
			logger.error(e.toString());
			throw e;
		}
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
