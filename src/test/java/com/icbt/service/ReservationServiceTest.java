package com.icbt.service;

import com.icbt.dao.reservationDao;
import com.icbt.dto.reservationDto;
import com.icbt.model.reservation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservationServiceTest {

    @Test
    void addReservation_shouldGenerateFirstReservationNo_andSave() {
        reservationDao dao = mock(reservationDao.class);
        when(dao.findLastReservationNo()).thenReturn(null);
        when(dao.existsByReservationNo("RES-0001")).thenReturn(false);

        reservationService service = new reservationService(dao);
        reservationDto dto = validDto();

        String result = service.addReservation(dto);

        assertEquals("RES-0001", result);

        ArgumentCaptor<reservation> captor = ArgumentCaptor.forClass(reservation.class);
        verify(dao).save(captor.capture());

        reservation saved = captor.getValue();
        assertEquals("RES-0001", saved.getReservationNo());
        assertEquals("Nimal Perera", saved.getGuestName());
        assertEquals("DELUXE", saved.getRoomType());
        assertEquals(LocalDate.of(2026, 5, 10), saved.getCheckIn());
        assertEquals(LocalDate.of(2026, 5, 12), saved.getCheckOut());
    }

    @Test
    void addReservation_shouldReturnValidationMessage_whenPhoneInvalid() {
        reservationDao dao = mock(reservationDao.class);
        when(dao.findLastReservationNo()).thenReturn("RES-0009");
        when(dao.existsByReservationNo("RES-0010")).thenReturn(false);

        reservationService service = new reservationService(dao);
        reservationDto dto = validDto();
        dto.setContact("invalid-phone");

        String result = service.addReservation(dto);

        assertEquals("Invalid contact number (e.g., 0771234567).", result);
        verify(dao, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void updateReservation_shouldReturnNotFound_whenReservationDoesNotExist() {
        reservationDao dao = mock(reservationDao.class);
        when(dao.findByReservationNo("RES-0100")).thenReturn(Optional.empty());

        reservationService service = new reservationService(dao);
        reservationDto dto = validDto();
        dto.setReservationNo("RES-0100");

        String result = service.updateReservation(dto);

        assertEquals("Reservation not found.", result);
        verify(dao, never()).update(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void updateReservation_shouldReturnOk_andUpdate_whenReservationExists() {
        reservationDao dao = mock(reservationDao.class);
        reservation existing = new reservation(
                "RES-0101", "Old", "Old", "0771111111", "STANDARD",
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 2)
        );

        when(dao.findByReservationNo("RES-0101")).thenReturn(Optional.of(existing));

        reservationService service = new reservationService(dao);
        reservationDto dto = validDto();
        dto.setReservationNo("RES-0101");

        String result = service.updateReservation(dto);

        assertEquals("OK", result);
        verify(dao).update(org.mockito.ArgumentMatchers.any(reservation.class));
    }

    @Test
    void cancelReservation_shouldDeleteAndReturnOk_whenReservationExists() {
        reservationDao dao = mock(reservationDao.class);
        when(dao.findByReservationNo("RES-0110")).thenReturn(Optional.of(new reservation()));

        reservationService service = new reservationService(dao);

        String result = service.cancelReservation("RES-0110");

        assertEquals("OK", result);
        verify(dao).deleteByReservationNo("RES-0110");
    }

    @Test
    void getReservationByPhone_shouldReturnEmpty_whenBlankPhone() {
        reservationDao dao = mock(reservationDao.class);
        reservationService service = new reservationService(dao);

        assertTrue(service.getReservationByPhone("   ").isEmpty());
        verify(dao, never()).findByPhone(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    void searchReservations_shouldReturnAll_whenKeywordBlank() {
        reservationDao dao = mock(reservationDao.class);
        List<reservation> expected = List.of(new reservation());
        when(dao.findAll()).thenReturn(expected);

        reservationService service = new reservationService(dao);

        List<reservation> result = service.searchReservations("   ");

        assertEquals(expected, result);
        verify(dao).findAll();
        verify(dao, never()).search(org.mockito.ArgumentMatchers.anyString());
    }

    private reservationDto validDto() {
        reservationDto dto = new reservationDto();
        dto.setGuestName("Nimal Perera");
        dto.setAddress("Galle");
        dto.setContact("0771234567");
        dto.setRoomType("Deluxe");
        dto.setCheckIn("2026-05-10");
        dto.setCheckOut("2026-05-12");
        return dto;
    }
}
