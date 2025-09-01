package DataBase.Service;

import DAOs.HotelDAO;
import models.Hotel;

import java.sql.SQLException;

public class HotelDatabaseService {
    //region fields
    private HotelDAO hotelDAO;
    //endregion

    //region Constructors
    public HotelDatabaseService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }
    //endregion

    //region Methods
    public boolean createHotelTable(Hotel hotel)  {
        if(hotel == null) {
            System.err.println("Cannot create hotel: Hotel object is null");
            return false;
        }

        if(hotel.getHotel_id() == null || hotel.getName() == null || hotel.getLocation() == null) {
            System.err.println("Cannot create hotel: Missing required hotel fields");
            return false;
        }

        boolean success = hotelDAO.createHotel(hotel);
        if (success) {
            System.out.println("Hotel '" + hotel.getName() + "' created successfully in database");
        }

        return success;
    }

    public Hotel getHotel(Integer hotelId) {
        if (hotelId == null) {
            System.err.println("Cannot retrieve hotel: Hotel ID is null");
            return null;
        }

        Hotel hotel = hotelDAO.getHotelById(hotelId);
        if (hotel == null) {
            System.out.println("Hotel with ID " + hotelId + " not found in database");
        }

        return hotel;
    }

    public boolean updateHotel(Hotel hotel) {
        if (hotel == null || hotel.getHotel_id() == null) {
            System.err.println("Cannot update hotel: Hotel or Hotel ID is null");
            return false;
        }

        boolean success = hotelDAO.updateHotel(hotel);
        if (success) {
            System.out.println("Hotel '" + hotel.getName() + "' updated successfully in database");
        }

        return success;
    }

    public boolean deleteHotel(Integer hotelId) {
        if (hotelId == null) {
            System.err.println("Cannot delete hotel: Hotel ID is null");
            return false;
        }

        boolean success = hotelDAO.deleteHotel(hotelId);
        if (success) {
            System.out.println("Hotel with ID " + hotelId + " deleted successfully from database");
        }

        return success;
    }
    //endregion
}
