package com.mapper;

import com.dto.PasswordDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordMapper implements RowMapper<PasswordDTO> {
    @Override
    public PasswordDTO mapRow(ResultSet rs, int i) throws SQLException {
        PasswordDTO passwordDTO= new PasswordDTO();
        passwordDTO.setPasswordID(rs.getLong("PASSWORD_ID"));
        passwordDTO.setPassword_(rs.getString("PASSWORD_"));
        passwordDTO.setUserIdFk(rs.getLong("USER_ID_FK"));
        return passwordDTO;
    }
}
