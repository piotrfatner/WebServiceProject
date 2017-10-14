package com.service;

import com.dao.LoginDao;
import com.dto.TokenDTO;
import com.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSideService {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public ResponseEntity<?> checkTokenValid(String insertedToken){
        TokenDTO tokenFromDB = LoginDao.getInstance().checkIfTokenValid(insertedToken, jdbcTemplate);
        if(tokenFromDB !=null){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public UserDTO getUserDatas(String insertedToken){

    }
}
