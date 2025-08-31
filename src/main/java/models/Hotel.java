package models;

import java.util.List;

public class Hotel {
    //region Fields
    private Integer hotel_id;
    private String name;
    private String Location;
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Reservation> reservations;
    //endregion

    //region Constructors
    public Hotel() {}

    public Hotel(Integer hotel_id, String name, String location, List<Room> rooms, List<Guest> guests, List<Reservation> reservations) {
        this.hotel_id = hotel_id;
        this.name = name;
        Location = location;
        this.rooms = rooms;
        this.guests = guests;
        this.reservations = reservations;
    }
    //endregion

    //region Getters

    public Integer getHotel_id() {
        return hotel_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return Location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    //endregion

    //region Setters

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    //endregion

}
