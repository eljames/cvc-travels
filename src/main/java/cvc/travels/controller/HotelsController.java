package cvc.travels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cvc.travels.business.Hotel;
import cvc.travels.service.HotelService;

@RestController
@RequestMapping(path="/api/hotels")
public class HotelsController {

	@Autowired
	private HotelService listService;
	
	@GetMapping(path="/city/{cityId}")
	public List<Hotel> hotels(@PathVariable int cityId) {
		return this.listService.hotels(cityId);
	}
	
	@GetMapping(path="/{hotelId}")
	public List<Hotel> hotel(@PathVariable int hotelId) {
		return this.listService.hotel(hotelId);
	}
	
}
