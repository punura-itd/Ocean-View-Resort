package com.icbt.dao;

import java.util.Optional;

public interface roomRateDao {
    Optional<Double> findRateByRoomType(String roomType);
}