package com.mapper;

import com.dto.TokenDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<TokenDTO> {
    @Override
    public TokenDTO mapRow(ResultSet rs, int i) throws SQLException {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenId(rs.getLong("TOKEN_ID"));
        tokenDTO.setToken(rs.getString("TOKEN"));
        tokenDTO.setExpirationDate(rs.getString("EXPIRATION_DATE"));
        tokenDTO.setUserIdFk(rs.getLong("USER_ID_FK"));
        return tokenDTO;
    }
}
