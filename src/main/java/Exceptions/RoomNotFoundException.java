package Exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException(Integer roomNumber, String additionalInfo) {
        super("Room with number '" + roomNumber + "' not found. " + additionalInfo);
    }
}
