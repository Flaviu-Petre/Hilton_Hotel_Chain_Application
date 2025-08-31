package Exceptions;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(String message) {
        super(message);
    }

    public RoomNotAvailableException(String roomNumber, String additionalInfo) {
        super("Room with number '" + roomNumber + "' is not available. " + additionalInfo);
    }
}
