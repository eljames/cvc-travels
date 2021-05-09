package cvc.travels.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import cvc.travels.business.RoomPartner;
import cvc.travels.business.TreatableException;
import cvc.travels.service.HotelService.InputParamCached;

public class HotelTotals {
	
	private final RoomPartner roomPartner;
	private final InputParamCached inputs;
	
	public HotelTotals(RoomPartner roomPartner, InputParamCached inputs) {
		this.roomPartner = roomPartner;
		this.inputs = inputs;
	}
	
	public BigDecimal total() throws TreatableException {
		return childPriceInDays().add(adultPriceInDays());
	}
	
	public BigDecimal adultPriceInDays() throws TreatableException {
		BigDecimal adultPrice = roomPartner.price.adult;
		long days = ChronoUnit.DAYS.between(inputs.checkinInDate(), inputs.checkinOutDate()) + 1;
		return adultPrice.multiply(new BigDecimal(days)).divide(new BigDecimal("0.7"), 2, RoundingMode.CEILING);
	}
	
	public BigDecimal childPriceInDays() throws TreatableException {
		BigDecimal childPrice = roomPartner.price.child;
		long days = ChronoUnit.DAYS.between(inputs.checkinInDate(), inputs.checkinOutDate()) + 1;
		return childPrice.multiply(new BigDecimal(days)).divide(new BigDecimal("0.7"), 2, RoundingMode.CEILING);
	}
}
