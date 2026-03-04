package com.icbt.service;

import com.icbt.dao.billDao;
import com.icbt.dao.reservationDao;
import com.icbt.dao.roomRateDao;
import com.icbt.dto.billDto;
import com.icbt.model.reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BillingServiceTest {

    @Test
    void generateBill_shouldReturnEmpty_whenReservationNumberBlank() {
        reservationDao reservationDao = mock(reservationDao.class);
        roomRateDao roomRateDao = mock(roomRateDao.class);
        billDao billDao = mock(billDao.class);

        billingService service = new billingService(reservationDao, roomRateDao, billDao);

        assertTrue(service.generateBill(" ").isEmpty());
        verify(reservationDao, never()).findByReservationNo(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    void generateBill_shouldReturnEmpty_whenReservationNotFound() {
        reservationDao reservationDao = mock(reservationDao.class);
        roomRateDao roomRateDao = mock(roomRateDao.class);
        billDao billDao = mock(billDao.class);

        when(reservationDao.findByReservationNo("RES-1001")).thenReturn(Optional.empty());

        billingService service = new billingService(reservationDao, roomRateDao, billDao);

        assertTrue(service.generateBill("RES-1001").isEmpty());
        verify(billDao, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void generateBill_shouldReturnEmpty_whenRoomRateMissing() {
        reservationDao reservationDao = mock(reservationDao.class);
        roomRateDao roomRateDao = mock(roomRateDao.class);
        billDao billDao = mock(billDao.class);

        reservation res = new reservation(
                "RES-1002", "Asha", "Colombo", "0771234567", "DELUXE",
                LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 3)
        );

        when(reservationDao.findByReservationNo("RES-1002")).thenReturn(Optional.of(res));
        when(roomRateDao.findRateByRoomType("DELUXE")).thenReturn(Optional.empty());

        billingService service = new billingService(reservationDao, roomRateDao, billDao);

        assertTrue(service.generateBill("RES-1002").isEmpty());
        verify(billDao, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void generateBill_shouldReturnBillAndSave_whenDataValid() {
        reservationDao reservationDao = mock(reservationDao.class);
        roomRateDao roomRateDao = mock(roomRateDao.class);
        billDao billDao = mock(billDao.class);

        reservation res = new reservation(
                "RES-1003", "Kamal", "Galle", "0712345678", "standard",
                LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 4)
        );

        when(reservationDao.findByReservationNo("RES-1003")).thenReturn(Optional.of(res));
        when(roomRateDao.findRateByRoomType("STANDARD")).thenReturn(Optional.of(12000.0));

        billingService service = new billingService(reservationDao, roomRateDao, billDao);

        Optional<billDto> result = service.generateBill("RES-1003");

        assertTrue(result.isPresent());
        assertEquals("RES-1003", result.get().getReservationNo());
        assertEquals("Kamal", result.get().getGuestName());
        assertEquals("STANDARD", result.get().getRoomType());
        assertEquals(3L, result.get().getNights());
        assertEquals(12000.0, result.get().getRatePerNight());
        assertEquals(36000.0, result.get().getTotal());

        verify(billDao).save(result.get());
    }
}
