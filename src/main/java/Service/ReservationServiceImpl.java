package Service;

import Interfaces.ReservationServiceInterface;
import Repository.ReservationRepository;
import models.Reservation;

public class ReservationServiceImpl implements ReservationServiceInterface {
    //region Fields
    private ReservationRepository reservationRepository;
    //endregion

    //region Constructors
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    //endregion

    //region Methods
    @Override
    public void makeReservationForRoom(Reservation reservation, Integer room_number) {
        reservationRepository.makeReservationForRoom(reservation, room_number);
    }

    @Override
    public void cancelReservation(Integer reservation_id) {
        reservationRepository.cancelReservation(reservation_id);
    }

    @Override
    public Reservation getReservationForHotel(Integer hotel_id) {
        return reservationRepository.getReservationForHotel(hotel_id);
    }
    //endregion

}
