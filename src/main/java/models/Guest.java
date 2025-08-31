package models;

public class Guest {
    //region Fields
    private Integer guest_id;
    private String email;
    private String name;
    private String phone_number;
    private Integer hotel_id;
    //endregion

    //region Constructors
    public Guest() {

    }

    public Guest(Integer guest_id, String email, String name, String phone_number, Integer hotel_id) {
        this.guest_id = guest_id;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.hotel_id = hotel_id;
    }
    //endregion

    //region Getters

    public Integer getGuest_id() {
        return guest_id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    //endregion

    //region Setters

    public void setGuest_id(Integer guest_id) {
        this.guest_id = guest_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    //endregion
}
