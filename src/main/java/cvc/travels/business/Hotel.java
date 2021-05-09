package cvc.travels.business;

import java.util.List;

public class Hotel {
	
	public Hotel(int id, String city, String name, List<Room> rooms) {
		this.id = id;
		this.city = city;
		this.name = name;
		this.rooms = rooms;
	}
	
	public int id;
	public String city;
    public String name;
    public List<Room> rooms;	 
}
