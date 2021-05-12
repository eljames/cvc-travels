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
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", city=" + city + ", name=" + name + ", rooms=" + rooms + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}	 
}
