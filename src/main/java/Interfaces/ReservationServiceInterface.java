package Interfaces;

import models.Reservation;

public interface ReservationServiceInterface {
    void makeReservationForRoom(Reservation reservation, Integer room_number);
    void cancelReservation(Integer reservation_id);
    Reservation getReservationForHotel(Integer hotel_id);
}
