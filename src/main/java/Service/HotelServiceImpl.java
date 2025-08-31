package Service;

import Interfaces.HotelServiceInterface;
import Repository.HotelRepository;
import models.Hotel;

public class HotelServiceImpl implements HotelServiceInterface {
    //region Fields
    private HotelRepository hotelRepository;
    //endregion

    //region Constructors
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    //endregion

    //region Methods

    @Override
    public Hotel getHotel(Integer hotel_id) {
        return hotelRepository.getHotel(hotel_id);
    }

    @Override
    public void addHotel(Hotel hotel) {
        hotelRepository.addHotel(hotel);
    }

    @Override
    public void displayHotelDetails(Integer hotel_id) {
        hotelRepository.displayHotelInformation(hotel_id);
    }

    //endregion
}
