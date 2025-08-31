package models;

public class Room {
    //region Fields
    private String room_number;
    private String type;
    private boolean is_available;
    private Integer hotel_id;
    //endregion

    //region Constructors
    public Room() {

    }

    public Room(String room_number, String type, boolean is_available, Integer hotel_id) {
        this.room_number = room_number;
        this.type = type;
        this.is_available = is_available;
        this.hotel_id = hotel_id;
    }
    //endregion

    //region Getters

    public String getRoom_number() {
        return room_number;
    }

    public String getType() {
        return type;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    //endregion

    //region Setters

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    //endregion
}
