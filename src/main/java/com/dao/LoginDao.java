package com.dao;

import com.dto.BookDTO;
import com.dto.PasswordDTO;
import com.dto.TokenDTO;
import com.dto.UserDTO;
import com.enums.EDbSqls;
import com.mapper.BookMapper;
import com.mapper.PasswordMapper;
import com.mapper.TokenMapper;
import com.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.print.Book;
import java.util.List;


public class LoginDao {


    public static LoginDao loginInstance = null;

    public LoginDao(){}

    public static LoginDao getInstance(){
        if(loginInstance == null){
            loginInstance = new LoginDao();
        }
        return loginInstance;
    }

    public UserDTO checkIfUserInDB(String login, JdbcTemplate jdbcTemplate){
        try{
            UserDTO userDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_USER_BY_LOGIN.getQuery(), new Object[]{login}, new UserMapper());
            return userDTO;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public PasswordDTO getPasswordForUserById(long userId, JdbcTemplate jdbcTemplate){
        PasswordDTO passwordDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_PASSWORD_BY_USER_ID.getQuery(), new Object[]{userId}, new PasswordMapper());
        return passwordDTO;
    }

    public TokenDTO getTokenForUserById(long userId, JdbcTemplate jdbcTemplate){
        try{

        TokenDTO tokenDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_TOKEN_BY_USER_ID.getQuery(), new Object[] {userId}, new TokenMapper());
        return tokenDTO;
        }catch(EmptyResultDataAccessException e){

        }
        return null;
    }

    public Integer insertNewTokenForUser(long userId, String token, String expirationDate, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(EDbSqls.INSERT_TOKEN_INTO_DB.getQuery(),new Object[] {token, expirationDate, userId});
    }

    public TokenDTO checkIfTokenValid(String token, JdbcTemplate jdbcTemplate){
        try {
            TokenDTO tokenDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_TOKEN_BY_TOKEN.getQuery(), new Object[]{token}, new TokenMapper());
            return tokenDTO;
        }catch(EmptyResultDataAccessException e){
        }
        return null;
    }

    public Integer checkIfUserAlreadyInDB(String login, String email, String firstName, String lastName, JdbcTemplate jdbcTemplate){
        Integer userInDB = jdbcTemplate.queryForInt(EDbSqls.CHECK_IF_USER_ALREADY_IN_DB.getQuery(), new Object[]{login, email, firstName, lastName});
        return userInDB;
    }

    public Integer getLastUserCardId(JdbcTemplate jdbcTemplate){
        Integer lastUserCardId = jdbcTemplate.queryForInt(EDbSqls.GET_LAST_USER_CARD_ID.getQuery());
        return lastUserCardId;
    }

    public Integer insertNewUser(String firstName, String lastName, String login, String email,Integer userCardId, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(EDbSqls.INSERT_NEW_USER.getQuery(),new Object[] {firstName,lastName,login,email,userCardId});
    }

    public Integer insertNewPassword(String password, long userId, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(EDbSqls.INSERT_NEW_PASSWORD.getQuery(), new Object[] {password, userId});
    }

    public List<BookDTO> getBooksForUser(long userId, JdbcTemplate jdbcTemplate){
        List<BookDTO> bookDTO= jdbcTemplate.query(EDbSqls.SELECT_BOOKS_FOR_USER.getQuery(),new Object[]{userId}, new BookMapper());
        return bookDTO;
    }

    public UserDTO getCurrentUser(long userId, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.queryForObject(EDbSqls.SELECT_USER_BY_ID.getQuery(), new Object[]{userId}, new UserMapper());
    }

    public List<BookDTO> getAllBooks(JdbcTemplate jdbcTemplate){
        List<BookDTO> bookDTO = jdbcTemplate.query(EDbSqls.SELECT_ALL_BOOKS.getQuery(), new BookMapper());
        return bookDTO;
    }

    public Integer hireBook(long userId,String bookId, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(EDbSqls.INSERT_NEW_BOOK_HIRE.getQuery(), new Object[]{userId, bookId});
    }
}
