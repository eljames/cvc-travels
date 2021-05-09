package cvc.travels.business;

import java.math.BigDecimal;

public class Room{
	
	public Room(int id, Category category, BigDecimal totalPrice, PriceDetail priceDetail) {
		this.id = id;
		this.category = category;
		this.totalPrice = totalPrice;
		this.priceDetail = priceDetail;
	}
	
    public int id;
	public Category category;
    public BigDecimal totalPrice;
    public PriceDetail priceDetail;
}