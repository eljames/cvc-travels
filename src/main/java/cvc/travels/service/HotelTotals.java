package cvc.travels.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import cvc.travels.business.InputParamCached;
import cvc.travels.business.RoomPartner;
import cvc.travels.business.TreatableException;

public class HotelTotals {
	
	private final RoomPartner roomPartner;
	private final InputParamCached inputs;
	
	public HotelTotals(RoomPartner roomPartner, InputParamCached inputs) {
		this.roomPartner = roomPartner;
		this.inputs = inputs;
	}
	
	public BigDecimal total() throws TreatableException {
		return totalAdultPrice().add(totalChildPrice());
	}
	
	public BigDecimal adultPricePerDay() {
		BigDecimal adultPrice = roomPartner.price.adult;
		return adultPrice.divide(new BigDecimal("0.7"), 2, RoundingMode.CEILING);
	}
	
	public BigDecimal childPricePerDay() {
		BigDecimal childPrice = roomPartner.price.child;
		return childPrice.divide(new BigDecimal("0.7"), 2, RoundingMode.CEILING);
	}
	
	private BigDecimal totalAdultPrice() {
		long days = ChronoUnit.DAYS.between(inputs.checkinInDate(), inputs.checkinOutDate()) + 1;
		return adultPricePerDay()
			.multiply(new BigDecimal(this.inputs.numberOfAdults))
			.multiply(new BigDecimal(days));
	}
	
	private BigDecimal totalChildPrice() {
		long days = ChronoUnit.DAYS.between(inputs.checkinInDate(), inputs.checkinOutDate()) + 1;
		return childPricePerDay()
			.multiply(new BigDecimal(this.inputs.numberOfChildren))
			.multiply(new BigDecimal(days));
	}
}
