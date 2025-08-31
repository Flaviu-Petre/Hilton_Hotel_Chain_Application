package Repository;

import Enums.ReservationStatus;
import models.Reservation;
import models.Room;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    //region fields
    private RoomRepository roomRepository;
    private List<Reservation> reservations;
    //endregion

    //region Constructors
    public ReservationRepository(RoomRepository roomRepository) {
        this.reservations = new ArrayList<>();
        this.roomRepository = roomRepository;
    }
    //endregion

    //region Methods
    public void makeReservationForRoom(Reservation reservation, String room_number) {
        Room roomToMakeReservation = roomRepository.getRoomByNumber(room_number);

        if(roomToMakeReservation.isIs_available()){
            reservation.setReservation_status(ReservationStatus.BOOKED);
            reservations.add(reservation);
            roomToMakeReservation.setIs_available(false);

            System.out.println("Reservation made successfully for room number: " + room_number);
        } else {
            System.out.println("Room number: " + room_number + " is not available for reservation.");
        }
    }

    public void cancelReservation(Integer reservation_id) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservation_id().equals(reservation_id)) {
                reservation.setReservation_status(ReservationStatus.CANCELLED);
                Room roomToCancelReservation = roomRepository.getRoomByNumber(reservation.getRoom_number());
                roomToCancelReservation.setIs_available(true);

                System.out.println("Reservation with ID: " + reservation_id + " has been cancelled.");
                return;
            }
        }
    }

    public Reservation getReservationForHotel(Integer hotel_id) {
        for (Reservation reservation : reservations) {
            if (reservation.getHotel_id().equals(hotel_id)) {
                return reservation;
            }
        }
        return null;
    }

    //endregion
}
