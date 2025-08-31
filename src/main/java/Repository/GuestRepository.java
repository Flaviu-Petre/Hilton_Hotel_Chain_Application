package Repository;

import models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {
    //region fields
    private List<Guest> guests;
    //endregion

    //region Constructors
    public GuestRepository() {
        this.guests = new ArrayList<>();
    }
    //endregion

    //region Methods
    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public List<Guest> getAllGuests() {
        return guests;
    }

    public Guest getGuestById(Integer guest_id) {
        for (Guest guest : guests) {
            if (guest.getGuest_id().equals(guest_id)) {
                return guest;
            }
        }
        return null;
    }

    public void displayAllGuests() {
        for (Guest guest : guests) {
            System.out.println(guest.getGuest_id() + " | " + guest.getName() + " | " + guest.getEmail() + " | " + guest.getPhone_number() + " | " + guest.getHotel_id());
        }
    }
    //endregion
}
