package Service;

import Interfaces.RoomServiceInterface;
import Repository.RoomRepository;
import models.Room;

import java.util.List;

public class RoomServiceImpl implements RoomServiceInterface {
    //region fields
    private RoomRepository roomRepository;
    //endregion

    //region Constructors
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    //endregion

    //region Methods

    @Override
    public void addRoom(Room room) {
        roomRepository.addRoom(room);
    }

    @Override
    public void displayRoomDetails(String room_number) {
        roomRepository.displayRoomDetails(room_number);
    }

    @Override
    public List<Room> getRoomsForHotel(Integer hotelId) {
        return roomRepository.getRoomsForHotel(hotelId);
    }

    //endregion
}
