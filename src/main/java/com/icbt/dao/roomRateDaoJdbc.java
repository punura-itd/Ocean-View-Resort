package com.icbt.dao;

import com.icbt.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class roomRateDaoJdbc implements roomRateDao {

    @Override
    public Optional<Double> findRateByRoomType(String roomType) {
        String sql = "SELECT rate_per_night FROM room_rates WHERE room_type=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, roomType);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getDouble("rate_per_night"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}