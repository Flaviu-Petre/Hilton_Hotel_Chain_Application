package Interfaces;

import models.Guest;

import java.util.List;

public interface GuestServiceInterface {
    void addGuest(Guest guest);
    List<Guest> getAllGuests();
    Guest getGuestById(Integer guest_id);
    void displayAllGuests();
}
