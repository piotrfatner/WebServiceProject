package com.service;

import com.dao.LoginDao;
import com.dto.*;
import com.enums.EDbSqls;
import com.mapper.UserMapper;
import com.security.Security;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LoginService {/*
    @Autowired
    private LoginMapper loginMapper;*/
    /*@Autowired
    public LoginDao loginD;*/
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getTokenForUser(LoginDTO loginDto){
        // sprawdzamy czy jest dany uzytkownik w bazie, haszujemy hasło md5 i spradzamy czy zgadza sie z danym uzytkownikiem
        // jezeli spoko to patrzymy do bazy czy jest token dla tego uzzytkownika, jak jest to pobieramy go i oddajemy w tej metodzie jak nie ma to generujemy
        // jak passy nieprawidlowe to zwracam pusty string
        UserDTO isUserInDb =LoginDao.getInstance().checkIfUserInDB(loginDto.getLogin(), jdbcTemplate);
        if(isUserInDb == null){
            return "";
        }
        PasswordDTO passwordDTO = LoginDao.getInstance().getPasswordForUserById(isUserInDb.getUserId(), jdbcTemplate);
        String  clientHash = Security.getInstance().md5(loginDto.getPassword());
        if(passwordDTO.getPassword_().equals(clientHash)){ // jesli rowne to bierzemy/ generujemy token
            TokenDTO tokenDTO = LoginDao.getInstance().getTokenForUserById(isUserInDb.getUserId(), jdbcTemplate);
            if(tokenDTO != null && Security.getInstance().getTimeStamp(tokenDTO.getExpirationDate()).after(new Timestamp(System.currentTimeMillis()))){ //  jezeli mamy wazny token w bazie
                return tokenDTO.getToken();
            }
            else{
                String generatedToken = Security.getInstance().generateToken(40);
                String expirationDate = Security.getInstance().getExpirationDate()+" 00:00:00";
                Integer insertTokenStatus = LoginDao.getInstance().insertNewTokenForUser(isUserInDb.getUserId(),generatedToken , expirationDate, jdbcTemplate);
                if(insertTokenStatus == 1){
                    tokenDTO = LoginDao.getInstance().getTokenForUserById(isUserInDb.getUserId(), jdbcTemplate);
                    return tokenDTO.getToken();
                }
            }
        }
            return "";
        //List<UserDTO> users = jdbcTemplate.query(EDbSqls.SELECT_TOKEN_FOR_USER.getQuery(), new UserMapper());
        /*String sql2 = "INSERT INTO TBL_USER(USER_FIRSTNAME, USER_LASTNAME, LOGIN, USER_CARD_ID) VALUES(?,?,?,?);"; // 'Aaron', 'Paul2', 'aaron2', 101
        jdbcTemplate.update(sql2, new Object[]{"Aaron", "Paul2", "aaron2",101});
*/

    }

    public ResponseEntity<?> signUp(RegistrationDTO registrationDTO){
        String login = registrationDTO.getLogin();
        String email = registrationDTO.getEmail();
        String firstName = registrationDTO.getFirstName();
        String lastName = registrationDTO.getLastName();
        String password = Security.getInstance().md5(registrationDTO.getPwd());
        Integer lastUserCardId = LoginDao.getInstance().getLastUserCardId(jdbcTemplate);
        Integer usersInDB = LoginDao.getInstance().checkIfUserAlreadyInDB(registrationDTO.getLogin(),registrationDTO.getEmail(), registrationDTO.getFirstName(), registrationDTO.getLastName(),jdbcTemplate);
        if(usersInDB !=0){ // jezeli user juz istnieje zwracam 400
            return new ResponseEntity<String>("Dany użytkownik już istnieje!",HttpStatus.valueOf(400));
        }
        Integer success = LoginDao.getInstance().insertNewUser(firstName,lastName,login,email,lastUserCardId+1,jdbcTemplate);
        long lastInsertedUserId = LoginDao.getInstance().checkIfUserInDB(login,jdbcTemplate).getUserId();
        Integer success2 = LoginDao.getInstance().insertNewPassword(password,lastInsertedUserId, jdbcTemplate);
        if(success==1 && success2==1){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<String>("Błąd podczas wstawiania!",HttpStatus.valueOf(400));
    }
}
