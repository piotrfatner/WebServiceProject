package com.dao;

import com.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDTO checkIfUserInDB(String login){
        return null;
    }

    public String getPasswordForUser(long userId){
        return "";
    }
}
