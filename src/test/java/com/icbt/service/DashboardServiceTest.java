package com.icbt.service;

import com.icbt.dao.dashboardDao;
import com.icbt.dto.dashboardStatsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DashboardServiceTest {

    @Test
    void getDashboardStats_shouldReturnDaoResult() {
        dashboardDao dao = mock(dashboardDao.class);
        dashboardStatsDto dto = new dashboardStatsDto();
        dto.setTotalReservations(10);

        when(dao.getStats()).thenReturn(dto);

        dashboardService service = new dashboardService(dao);

        dashboardStatsDto result = service.getDashboardStats();

        assertSame(dto, result);
        verify(dao).getStats();
    }
}
