package com.service;

import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.enums.EDbSqls;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {/*
    @Autowired
    private LoginMapper loginMapper;*/
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<UserDTO> getTokenForUser(LoginDTO loginDto){
        // sprawdzamy czy jest dany uzytkownik w bazie, haszujemy hasło md5 i spradzamy czy zgadza sie z danym uzytkownikiem
        // jezeli spoko to patrzymy do bazy czy jest token dla tego uzzytkownika, jak jest to pobieramy go i oddajemy w tej metodzie jak nie ma to generujemy
        // jak passy nieprawidlowe to zwracam pusty string

        String sql = "SELECT * FROM TBL_USER;";
        List<UserDTO> users = jdbcTemplate.query(EDbSqls.SELECT_TOKEN_FOR_USER.getQuery(), new UserMapper());
        /*String sql2 = "INSERT INTO TBL_USER(USER_FIRSTNAME, USER_LASTNAME, LOGIN, USER_CARD_ID) VALUES(?,?,?,?);"; // 'Aaron', 'Paul2', 'aaron2', 101
        jdbcTemplate.update(sql2, new Object[]{"Aaron", "Paul2", "aaron2",101});
*/

        return users;
    }
}
