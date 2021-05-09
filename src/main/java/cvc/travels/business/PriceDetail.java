package cvc.travels.business;

import java.math.BigDecimal;

public class PriceDetail {
	
	public BigDecimal pricePerDayAdult;
    public BigDecimal pricePerDayChild;
    
    public PriceDetail(BigDecimal pricePerDayAdult, BigDecimal pricePerDayChild) {
		this.pricePerDayAdult = pricePerDayAdult;
		this.pricePerDayChild = pricePerDayChild;
	}
	
	public PriceDetail() {
		
	}
}
