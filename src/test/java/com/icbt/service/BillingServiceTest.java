package com.icbt.service;

public class BillingServiceTest {
    @Test
    void calculateNights_shouldWork() {
        RoomRateDao dao = mock(RoomRateDao.class);
        BillingService svc = new BillingService(dao);

        Reservation r = new Reservation(
                "R100", "Asha", "Galle", "0771234567",
                "DELUXE", LocalDate.of(2026,2,1), LocalDate.of(2026,2,4)
        );

        assertEquals(3, svc.calculateNights(r));
    }

    @Test
    void calculateTotal_shouldMultiplyNightsByRate() {
        RoomRateDao dao = mock(RoomRateDao.class);
        when(dao.findRateByRoomType("STANDARD")).thenReturn(Optional.of(10000.0));

        BillingService svc = new BillingService(dao);

        Reservation r = new Reservation(
                "R101", "Asha", "Galle", "0771234567",
                "STANDARD", LocalDate.of(2026,2,1), LocalDate.of(2026,2,3)
        );

        assertEquals(20000.0, svc.calculateTotal(r));
    }

    @Test
    void calculateTotal_shouldThrow_whenRoomTypeUnknown() {
        RoomRateDao dao = mock(RoomRateDao.class);
        when(dao.findRateByRoomType("UNKNOWN")).thenReturn(Optional.empty());

        BillingService svc = new BillingService(dao);

        Reservation r = new Reservation(
                "R102", "Asha", "Galle", "0771234567",
                "UNKNOWN", LocalDate.of(2026,2,1), LocalDate.of(2026,2,3)
        );

        assertThrows(IllegalArgumentException.class, () -> svc.calculateTotal(r));
    }
}
