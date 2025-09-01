package Repository;

import Enums.ReservationStatus;
import models.Reservation;
import models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationRepositoryTest {

    @Mock
    private RoomRepository roomRepository;

    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        // Use the mocked roomRepository, don't create new instances
        reservationRepository = new ReservationRepository(roomRepository);
    }

    @Test
    void testMakeReservationForRoomWhenRoomIsAvailable() {

        String roomNumber = "101";
        Integer reservationId = 1;
        Integer guestId = 1;
        Integer hotelId = 1;
        LocalDate checkInDate = LocalDate.of(2025, 9, 10);
        LocalDate checkOutDate = LocalDate.of(2025, 9, 15);

        Room availableRoom = new Room(roomNumber, "Single", true, hotelId);

        Reservation reservation = new Reservation(
                reservationId,
                guestId,
                roomNumber,
                null,
                checkInDate,
                checkOutDate,
                hotelId
        );

        when(roomRepository.getRoomByNumber(roomNumber)).thenReturn(availableRoom);

        reservationRepository.makeReservationForRoom(reservation, roomNumber);

        verify(roomRepository, times(1)).getRoomByNumber(roomNumber);
        assertFalse(availableRoom.isIs_available());
        assertEquals(ReservationStatus.BOOKED, reservation.getReservation_status());
    }


}