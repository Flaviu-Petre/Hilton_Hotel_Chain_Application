package Interfaces;

import models.Hotel;

public interface HotelServiceInterface {
    void addHotel(Hotel hotel);
    Hotel getHotel(Integer hotel_id);
    void displayHotelDetails(Integer hotel_id);
}
