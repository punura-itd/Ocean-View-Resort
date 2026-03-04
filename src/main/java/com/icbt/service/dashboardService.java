package com.icbt.service;

import com.icbt.dao.dashboardDao;
import com.icbt.dto.dashboardStatsDto;

public class dashboardService {

    private final dashboardDao dashboardDao;

    public dashboardService(dashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    public dashboardStatsDto getDashboardStats() {
        return dashboardDao.getStats();
    }
}