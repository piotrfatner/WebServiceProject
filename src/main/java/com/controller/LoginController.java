package com.controller;

import com.dto.LoginDTO;
import com.service.LoginService;
import org.springframework.http.MediaType;
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
        System.out.println(loginService.getTokenForUser(loginDto));
        return "fakdfhkdsbgfhsnaj3432kcnkxdi";
    }


}
