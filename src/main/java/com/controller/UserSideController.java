package com.controller;

import com.dto.UserDTO;
import com.mysql.fabric.Response;
import com.service.UserSideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class UserSideController {
    @Autowired
    UserSideService userSideService;

    @RequestMapping(value="/checkTokenValid", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> checkTokenValid(@RequestBody String insertedToken){
        return userSideService.checkTokenValid(insertedToken);
    }

    @RequestMapping(value = "/getUserDatas/{token}", method = RequestMethod.GET)
    public UserDTO getUserDatas(@PathVariable("token") String insertedToken){
        //return userSideService.getUserDatas(insertedToken);
        return null;
    }
}
