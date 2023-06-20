package com.boardapp.boardapi.user.service;

import javax.sql.DataSource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private DataSource dataSource;

    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
