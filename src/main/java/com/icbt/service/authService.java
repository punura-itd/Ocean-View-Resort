package com.icbt.service;
import com.icbt.dao.userDao;
import com.icbt.dto.loginDto;


public class authService {
    private userDao userDao;

    public authService(userDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(loginDto dto) {

        if (dto == null || dto.getUsername() == null || dto.getPassword() == null)
            return false;

        return userDao.validateUser(dto.getUsername(), dto.getPassword());
    }
}
