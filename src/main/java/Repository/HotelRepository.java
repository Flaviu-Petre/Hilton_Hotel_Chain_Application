package Repository;

import models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelRepository {
    //region Fields
    private List<Hotel> hotels;
    //endregion

    //region Constructors
    public HotelRepository() {
        this.hotels = new ArrayList<>();
    }
    //endregion

    //region methods
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotels;
    }

    public Hotel getHotel(Integer hotel_id) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotel_id().equals(hotel_id)) {
                return hotel;
            }
        }
        System.out.println("Hotel not found");
        return null;
    }

    public void displayHotelInformation(Integer hotel_id) {
        Hotel hotel = getHotel(hotel_id);
        if (hotel == null) {
            System.out.println("No hotel information to display.");
            return;
        }
        System.out.println("Hotel ID: " + hotel.getHotel_id());
        System.out.println("Hotel Name: " + hotel.getName());
        System.out.println("Location: " + hotel.getLocation());
        System.out.println("Number of Rooms: " + (hotel.getRooms() != null ? hotel.getRooms().size() : 0));
        System.out.println("Number of Guests: " + (hotel.getGuests() != null ? hotel.getGuests().size() : 0));
        System.out.println("Number of Reservations: " + (hotel.getReservations() != null ? hotel.getReservations().size() : 0));
    }
    //endregion
}
