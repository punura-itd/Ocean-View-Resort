package com.icbt.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReservationTest {

    @Test
    void allArgsConstructor_shouldSetAllFields() {
        LocalDate checkIn = LocalDate.of(2026, 3, 1);
        LocalDate checkOut = LocalDate.of(2026, 3, 5);

        reservation model = new reservation(
                "R-001",
                "Nimal Perera",
                "Galle",
                "0771234567",
                "DELUXE",
                checkIn,
                checkOut
        );

        assertEquals("R-001", model.getReservationNo());
        assertEquals("Nimal Perera", model.getGuestName());
        assertEquals("Galle", model.getAddress());
        assertEquals("0771234567", model.getContact());
        assertEquals("DELUXE", model.getRoomType());
        assertEquals(checkIn, model.getCheckIn());
        assertEquals(checkOut, model.getCheckOut());
    }

    @Test
    void noArgsConstructor_andSetters_shouldUpdateFields() {
        reservation model = new reservation();
        LocalDate checkIn = LocalDate.of(2026, 4, 10);
        LocalDate checkOut = LocalDate.of(2026, 4, 12);

        assertNull(model.getReservationNo());
        assertNull(model.getGuestName());
        assertNull(model.getAddress());
        assertNull(model.getContact());
        assertNull(model.getRoomType());
        assertNull(model.getCheckIn());
        assertNull(model.getCheckOut());

        model.setReservationNo("R-002");
        model.setGuestName("Kamal Silva");
        model.setAddress("Matara");
        model.setContact("0712345678");
        model.setRoomType("STANDARD");
        model.setCheckIn(checkIn);
        model.setCheckOut(checkOut);

        assertEquals("R-002", model.getReservationNo());
        assertEquals("Kamal Silva", model.getGuestName());
        assertEquals("Matara", model.getAddress());
        assertEquals("0712345678", model.getContact());
        assertEquals("STANDARD", model.getRoomType());
        assertEquals(checkIn, model.getCheckIn());
        assertEquals(checkOut, model.getCheckOut());
    }
}
