package Interfaces;

import models.Room;

import java.util.List;

public interface RoomServiceInterface {
    void addRoom(Room room);
    void displayRoomDetails(String room_number);
    Room getRoomByNumber(String room_number);
    List<Room> getRoomsForHotel(Integer hotelId);
    List<Room> getAllRooms();
}
