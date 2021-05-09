package cvc.travels.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cvc.travels.business.Category;
import cvc.travels.business.Hotel;
import cvc.travels.business.HotelPartner;
import cvc.travels.business.InputParameters;
import cvc.travels.business.PriceDetail;
import cvc.travels.business.Room;
import cvc.travels.business.RoomPartner;
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
		return newPriceHotels(hotels, cached);
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
		return newPriceHotels(hotels, cached);
	}
	
	private List<Hotel> newPriceHotels(List<HotelPartner> hotelsPartner, InputParamCached inputs) throws TreatableException {
		List<Hotel> hotels = new ArrayList<Hotel>();
		for(HotelPartner hotelPartner : hotelsPartner) {
			hotels.add(newPriceHotel(hotelPartner, inputs));
		}
		return hotels;
	}


	private Hotel newPriceHotel(HotelPartner hotelPartner, InputParamCached inputs) throws TreatableException {
		Hotel hotel = new Hotel(
			hotelPartner.id,
			hotelPartner.cityName,
			hotelPartner.name,
			TotalRoomsPartner(hotelPartner.rooms, inputs)
		);
		return hotel;
	}


	private List<Room> TotalRoomsPartner(List<RoomPartner> roomsPartner, InputParamCached inputs) throws TreatableException {
		List<Room> rooms = new ArrayList<>();
		for(RoomPartner roomPartner : roomsPartner) {
			rooms.add(totalRoomPartner(roomPartner, inputs));
		}
		return rooms;
	}


	private Room totalRoomPartner(RoomPartner roomPartner, InputParamCached inputs) throws TreatableException {
		HotelTotals hoteltotals = new HotelTotals(roomPartner, inputs);
		Room room = new Room(
			roomPartner.roomID,
			new Category(roomPartner.categoryName),
			hoteltotals.total(),
			new PriceDetail(
				hoteltotals.adultPriceInDays(),
				hoteltotals.childPriceInDays()
			)
		);
		return room;
	}
	
	static class InputParamCached {
		public LocalDate checkInDate;
		public LocalDate checkOutDate;
		public int numberOfAdults;
		public int numberOfChildren;
		
		public InputParamCached(LocalDate checkInDate, LocalDate checkOutDate, int numberOfAdults, int numberOfChildren) {
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			this.numberOfAdults = numberOfAdults;
			this.numberOfChildren = numberOfChildren;
		}
		
		
		public LocalDate checkinInDate() throws TreatableException {
			return this.checkInDate;
		}
		
		public LocalDate checkinOutDate() throws TreatableException {
			return this.checkOutDate;
		}
	}
}
