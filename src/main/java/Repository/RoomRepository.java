package Repository;

import models.Hotel;
import models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    //region fields
    private List<Room> rooms;
    HotelRepository hotelRepository;
    //endregion

    //region Constructors
    public RoomRepository() {
        this.rooms = new ArrayList<>();
        this.hotelRepository = new HotelRepository();
    }
    //endregion

    //region Methods
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void displayRoomDetails(String room_number) {
        for (Room room : rooms) {
            if (room.getRoom_number().equals(room_number)) {
                System.out.println("Room Number: " + room.getRoom_number());
                System.out.println("Type: " + room.getType());
                System.out.println("Is Available: " + room.isIs_available());
                System.out.println("Hotel ID: " + room.getHotel_id());
                return;
            }
        }
        System.out.println("Room not found");
    }

    public Room getRoomByNumber(String room_number) {
        for (Room room : rooms) {
            if (room.getRoom_number().equals(room_number)) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getRoomsForHotel(Integer hotelId) {
        Hotel searchHotel = hotelRepository.getHotel(hotelId);
        if (searchHotel == null) {
            System.out.println("Hotel not found");
            return null;
        }
        return searchHotel.getRooms();
    }
    //endregion
}
