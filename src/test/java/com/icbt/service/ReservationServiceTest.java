//package com.icbt.service;
//
//import com.icbt.model.reservation;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class ReservationServiceTest {
//    @Test
//    void addReservation_shouldSave_whenValidAndUnique() {
//        reservationDao dao = mock(reservationDao.class);
//        when(dao.existsByReservationNo("R001")).thenReturn(false);
//
//        ReservationService svc = new ReservationService(dao);
//
//        reservation r = new reservation(
//                "R001", "Nimal Perera", "Galle", "0771234567",
//                "DELUXE", LocalDate.of(2026,2,20), LocalDate.of(2026,2,22)
//        );
//
//        assertTrue(svc.addReservation(r));
//        verify(dao, times(1)).save(r);
//    }
//
//    @Test
//    void addReservation_shouldReject_whenDuplicateReservationNo() {
//        reservationDao dao = mock(reservationDao.class);
//        when(dao.existsByReservationNo("R001")).thenReturn(true);
//
//        ReservationService svc = new ReservationService(dao);
//
//        reservation r = new reservation(
//                "R001", "Nimal", "Galle", "0771234567",
//                "DELUXE", LocalDate.of(2026,2,20), LocalDate.of(2026,2,22)
//        );
//
//        assertFalse(svc.addReservation(r));
//        verify(dao, never()).save(any());
//    }
//
//    @Test
//    void getReservation_shouldReturnEmpty_whenBlank() {
//        reservationDao dao = mock(reservationDao.class);
//        ReservationService svc = new ReservationService(dao);
//
//        assertTrue(svc.getReservation(" ").isEmpty());
//        verify(dao, never()).findByReservationNo(anyString());
//    }
//
//    @Test
//    void getReservation_shouldReturnValue_whenFound() {
//        reservationDao dao = mock(reservationDao.class);
//        ReservationService svc = new ReservationService(dao);
//
//        reservation r = new reservation(
//                "R009", "Kamal", "Matara", "0770000000",
//                "STANDARD", LocalDate.of(2026,3,1), LocalDate.of(2026,3,3)
//        );
//
//        when(dao.findByReservationNo("R009")).thenReturn(Optional.of(r));
//
//        Optional<reservation> found = svc.getReservation("R009");
//        assertTrue(found.isPresent());
//        assertEquals("Kamal", found.get().getGuestName());
//    }
//}
