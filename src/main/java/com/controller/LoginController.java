package com.controller;

import com.dto.LoginDTO;
import com.dto.RegistrationDTO;
import com.security.Security;
import com.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @RequestMapping(value="/authorization/login2", method = RequestMethod.GET)
    public String authorizate22(){
        System.out.println("hello motherfuckers22!");
        return "OK";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody String authorizate(@RequestBody LoginDTO loginDto){
        return loginService.getTokenForUser(loginDto);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> signUp(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        return loginService.signUp(registrationDTO);
    }




}
