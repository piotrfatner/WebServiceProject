package com.mapper;

import com.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDTO>{
    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(rs.getInt("USER_ID"));
        userDTO.setLogin(rs.getString("LOGIN"));
        userDTO.setUserFirstName(rs.getString("USER_FIRSTNAME"));
        userDTO.setUserLastName(rs.getString("USER_LASTNAME"));
        userDTO.setUserCardId(rs.getInt("USER_CARD_ID"));
        return userDTO;
    }
}
