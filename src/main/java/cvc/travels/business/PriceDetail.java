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

	@Override
	public String toString() {
		return "PriceDetail [pricePerDayAdult=" + pricePerDayAdult + ", pricePerDayChild=" + pricePerDayChild + "]";
	}

	public BigDecimal getPricePerDayAdult() {
		return pricePerDayAdult;
	}

	public void setPricePerDayAdult(BigDecimal pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}

	public BigDecimal getPricePerDayChild() {
		return pricePerDayChild;
	}

	public void setPricePerDayChild(BigDecimal pricePerDayChild) {
		this.pricePerDayChild = pricePerDayChild;
	}
}
