package com.icbt.dao;

import com.icbt.model.reservation;
import java.util.Optional;

public interface reservationDao {

    String findLastReservationNo();

    boolean existsByReservationNo(String reservationNo);

    void save(reservation reservation);

    Optional<reservation> findByReservationNo(String reservationNo);
}