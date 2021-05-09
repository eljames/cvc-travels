package cvc.travels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cvc.travels.business.InputParameters;
import cvc.travels.business.TreatableException;
import cvc.travels.service.HotelService;

@RestController
@RequestMapping(path="/api/hotels")
public class HotelsController {

	@Autowired
	private HotelService listHotels;
	
	@GetMapping(path="/city/{cityId}")
	public ResponseEntity<Object> hotels(@PathVariable int cityId, InputParameters inputs) {
		try {
			return ResponseEntity.ok(this.listHotels.hotelsByCity(cityId, inputs));
		} catch (TreatableException e) {
			return ResponseEntity
				.unprocessableEntity()
				.body(e);
		}
	}
	
	@GetMapping(path="/{hotelId}")
	public ResponseEntity<Object> hotel(@PathVariable int hotelId, InputParameters inputs) {
		try {
			return ResponseEntity.ok(listHotels.hotel(hotelId, inputs));
		} catch (TreatableException e) {
			return ResponseEntity
				.unprocessableEntity()
				.body(e);
		}
	}
}
