package com.icbt.dao;

import com.icbt.model.Reservation;

import java.util.Optional;

public interface ReservationDao {
    boolean existsByReservationNo(String reservationNo);
    void save(Reservation reservation);
    Optional<Reservation> findByReservationNo(String reservationNo);
}