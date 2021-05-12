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
	@Override
	public String toString() {
		return "Room [id=" + id + ", category=" + category + ", totalPrice=" + totalPrice + ", priceDetail="
				+ priceDetail + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public PriceDetail getPriceDetail() {
		return priceDetail;
	}
	public void setPriceDetail(PriceDetail priceDetail) {
		this.priceDetail = priceDetail;
	}
}