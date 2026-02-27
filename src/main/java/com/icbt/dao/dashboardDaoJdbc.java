package com.icbt.dao;

import com.icbt.dto.dashboardStatsDto;
import com.icbt.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dashboardDaoJdbc implements dashboardDao {

    @Override
    public dashboardStatsDto getStats() {
        dashboardStatsDto dto = new dashboardStatsDto();

        dto.setTotalReservations(getInt("SELECT COUNT(*) AS c FROM reservations"));
        dto.setTodayCheckIns(getInt("SELECT COUNT(*) AS c FROM reservations WHERE check_in = CURDATE()"));
        dto.setTodayCheckOuts(getInt("SELECT COUNT(*) AS c FROM reservations WHERE check_out = CURDATE()"));
        dto.setTotalBills(getInt("SELECT COUNT(*) AS c FROM bills"));
        dto.setTotalRevenue(getDouble("SELECT COALESCE(SUM(total), 0) AS s FROM bills"));

        return dto;
    }

    private int getInt(String sql) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double getDouble(String sql) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}