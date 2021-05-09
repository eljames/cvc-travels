package cvc.travels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		
		InputParamCached cached = new InputParamCached(
				inputs.checkinInDate(),
				inputs.checkinOutDate(),
				inputs.getNumberOfAdults(),
				inputs.getNumberOfChildren()
		);
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
		InputParamCached cached = new InputParamCached(
			inputs.checkinInDate(),
			inputs.checkinOutDate(),
			inputs.getNumberOfAdults(),
			inputs.getNumberOfChildren()
		);
		List<HotelPartner> hotels = this.hotelPartner.hotel(hotelId);
		return new MapPartner().mapToThisBusiness(hotels, cached);
	}
}
