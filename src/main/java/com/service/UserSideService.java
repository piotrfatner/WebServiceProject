package com.service;

import com.dao.LoginDao;
import com.dto.BookDTO;
import com.dto.TokenDTO;
import com.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;

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

    public ResponseEntity<?> getUserDatas(String insertedToken){
        TokenDTO tokenDTO = LoginDao.getInstance().checkIfTokenValid(insertedToken, jdbcTemplate);
        if(tokenDTO!=null){
            return  new ResponseEntity<UserDTO>(LoginDao.getInstance().getCurrentUser(tokenDTO.getUserIdFk(),jdbcTemplate),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> getBookHireForUser(String token){
        // checkTokenValid...
        TokenDTO tokenDTO = LoginDao.getInstance().checkIfTokenValid(token, jdbcTemplate);
        if(tokenDTO!=null){
            return  new ResponseEntity<List<BookDTO>>(LoginDao.getInstance().getBooksForUser(tokenDTO.getUserIdFk(), jdbcTemplate),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> getAllBooks(String token){
        // checkTokenValid...
        TokenDTO tokenDTO = LoginDao.getInstance().checkIfTokenValid(token, jdbcTemplate);
        if(tokenDTO!=null){
            return  new ResponseEntity<List<BookDTO>>(LoginDao.getInstance().getAllBooks(jdbcTemplate),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> hireBook(String token, String bookId){
        TokenDTO tokenDTO = LoginDao.getInstance().checkIfTokenValid(token, jdbcTemplate);
        if(tokenDTO!=null){
            return  new ResponseEntity(LoginDao.getInstance().hireBook(tokenDTO.getUserIdFk(),bookId,jdbcTemplate),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
