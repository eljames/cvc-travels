package cvc.travels.business;

import java.util.ArrayList;
import java.util.List;

import cvc.travels.service.HotelTotals;

public class MapPartner {
	
	
	public List<Hotel> mapToThisBusiness(List<HotelPartner> hotelsPartner, InputParamCached inputs) throws TreatableException {
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
			totalRoomsPartner(hotelPartner.rooms, inputs)
		);
		return hotel;
	}

	private List<Room> totalRoomsPartner(List<RoomPartner> roomsPartner, InputParamCached inputs) throws TreatableException {
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
				hoteltotals.adultPricePerDay(),
				hoteltotals.childPricePerDay()
			)
		);
		return room;
	}
}
