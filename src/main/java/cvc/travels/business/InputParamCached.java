package cvc.travels.business;

import java.time.LocalDate;

public class InputParamCached {
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
	
	
	public LocalDate checkinInDate() {
		return this.checkInDate;
	}
	
	public LocalDate checkinOutDate() {
		return this.checkOutDate;
	}
}