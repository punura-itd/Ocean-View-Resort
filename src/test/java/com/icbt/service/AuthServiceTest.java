package com.icbt.service;

import com.icbt.dao.userDao;
import com.icbt.dto.loginDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Test
    void login_shouldReturnFalse_whenDtoIsNull() {
        userDao dao = mock(userDao.class);
        authService service = new authService(dao);

        assertFalse(service.login(null));
        verifyNoInteractions(dao);
    }

    @Test
    void login_shouldReturnFalse_whenUsernameOrPasswordIsNull() {
        userDao dao = mock(userDao.class);
        authService service = new authService(dao);

        assertFalse(service.login(new loginDto(null, "1234")));
        assertFalse(service.login(new loginDto("admin", null)));

        verifyNoInteractions(dao);
    }

    @Test
    void login_shouldDelegateToDao_whenInputIsValid() {
        userDao dao = mock(userDao.class);
        when(dao.validateUser("admin", "1234")).thenReturn(true);

        authService service = new authService(dao);

        assertTrue(service.login(new loginDto("admin", "1234")));
        verify(dao).validateUser("admin", "1234");
    }
}
