package com.dao;

import com.dto.PasswordDTO;
import com.dto.TokenDTO;
import com.dto.UserDTO;
import com.enums.EDbSqls;
import com.mapper.PasswordMapper;
import com.mapper.TokenMapper;
import com.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;


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
        UserDTO userDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_USER_BY_LOGIN.getQuery(), new Object[]{login}, new UserMapper());
        if(userDTO == null){
            return null;
        }
        return userDTO;
    }

    public PasswordDTO getPasswordForUserById(long userId, JdbcTemplate jdbcTemplate){
        PasswordDTO passwordDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_PASSWORD_BY_USER_ID.getQuery(), new Object[]{userId}, new PasswordMapper());
        return passwordDTO;
    }

    public TokenDTO getTokenForUserById(long userId, JdbcTemplate jdbcTemplate){
        TokenDTO tokenDTO = jdbcTemplate.queryForObject(EDbSqls.SELECT_TOKEN_BY_USER_ID.getQuery(), new Object[] {userId}, new TokenMapper());
        return tokenDTO;
    }

    public Integer insertNewTokenForUser(long userId, String token, String expirationDate, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(EDbSqls.INSERT_TOKEN_INTO_DB.getQuery(),new Object[] {token, expirationDate, userId});
    }
}
