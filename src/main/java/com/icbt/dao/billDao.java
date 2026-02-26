package com.icbt.dao;

import com.icbt.dto.billDto;
import java.util.Optional;

public interface billDao {
    void save(billDto bill);
    Optional<billDto> findByReservationNo(String reservationNo);
}