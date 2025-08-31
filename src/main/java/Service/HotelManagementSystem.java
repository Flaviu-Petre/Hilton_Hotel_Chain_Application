package Service;

import Interfaces.GuestServiceInterface;
import Interfaces.HotelServiceInterface;
import Interfaces.ReservationServiceInterface;
import Interfaces.RoomServiceInterface;
import Repository.GuestRepository;
import Repository.HotelRepository;
import Repository.ReservationRepository;
import Repository.RoomRepository;
import models.Guest;
import models.Hotel;
import models.Room;

import java.util.List;

public class HotelManagementSystem {
    //region Fields
        //region Services
        private HotelServiceInterface hotelService;
        private GuestServiceInterface guestService;
        private RoomServiceInterface roomService;
        private ReservationServiceInterface reservationService;
        //endregion

        //region Repositories
        private HotelRepository hotelRepository;
        private GuestRepository guestRepository;
        private RoomRepository roomRepository;
        private ReservationRepository reservationRepository;
        //endregion
    //endregion

    //region Constructors
    public HotelManagementSystem() {
        // Initialize Repositories
        this.hotelRepository = new HotelRepository();
        this.guestRepository = new GuestRepository();
        this.roomRepository = new RoomRepository(hotelRepository);
        this.reservationRepository = new ReservationRepository(roomRepository);

        // Initialize Services
        this.hotelService = new HotelServiceImpl(hotelRepository);
        this.guestService = new GuestServiceImpl(guestRepository);
        this.roomService = new RoomServiceImpl(roomRepository);
        this.reservationService = new ReservationServiceImpl(reservationRepository);
    }
    //endregion

    //region Hotel Management Methods
    public void addHotel(Hotel hotel) {
        hotelService.addHotel(hotel);
        System.out.println("Hotel '" + hotel.getName() + "' added successfully.");
    }

    public Hotel getHotel(Integer hotelId) {
        return hotelService.getHotel(hotelId);
    }

    public void displayHotelDetails(Integer hotelId) {
        hotelService.displayHotelDetails(hotelId);
    }
    //endregion

    //region Guest Management Methods
    public void addGuest(Guest guest) {
        guestService.addGuest(guest);
        System.out.println("Guest '" + guest.getName() + "' added successfully.");
    }

    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    public Guest getGuestById(Integer guestId) {
        return guestService.getGuestById(guestId);
    }


    public void displayAllGuests() {
        System.out.println("=== ALL GUESTS ===");
        guestService.displayAllGuests();
    }
    //endregion

    //region Room Management Methods
    public void addRoom(Room room) {
        roomService.addRoom(room);
        System.out.println("Room " + room.getRoom_number() + " added successfully.");
    }

    public void displayRoomDetails(String roomNumber) {
        roomService.displayRoomDetails(roomNumber);
    }

    public Room getRoomByNumber(String roomNumber) {
        return roomService.getRoomByNumber(roomNumber);
    }

    public List<Room> getRoomsForHotel(Integer hotelId) {
        return roomService.getRoomsForHotel(hotelId);
    }
    //endregion

    //region Reservation Management Methods
    public void makeReservationForRoom(models.Reservation reservation, String room_number) {
        reservationService.makeReservationForRoom(reservation, room_number);
        System.out.println("Reservation made successfully for Room " + room_number);
    }

    public void cancelReservation(Integer reservation_id) {
        reservationService.cancelReservation(reservation_id);
        System.out.println("Reservation with ID " + reservation_id + " cancelled successfully.");
    }

    public models.Reservation getReservationForHotel(Integer hotel_id) {
        return reservationService.getReservationForHotel(hotel_id);
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    //endregion

}
