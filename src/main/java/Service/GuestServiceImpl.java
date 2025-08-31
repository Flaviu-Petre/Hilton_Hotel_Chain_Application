package Service;

import Interfaces.GuestServiceInterface;
import Repository.GuestRepository;
import models.Guest;

import java.util.List;

public class GuestServiceImpl implements GuestServiceInterface {
    //region Fields
    private GuestRepository guestRepository;
    //endregion

    //region Constructors
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    //endregion

    //region Methods

    @Override
    public void addGuest(Guest guest) {
        guestRepository.addGuest(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.getAllGuests();
    }

    @Override
    public Guest getGuestById(Integer guest_id) {
        return guestRepository.getGuestById(guest_id);
    }

    @Override
    public void displayAllGuests() {
        guestRepository.displayAllGuests();
    }

    //endregion

}
