package com.controller;

import com.dto.BookDTO;
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
    public ResponseEntity<?> getUserDatas(@PathVariable("token") String insertedToken){
        return userSideService.getUserDatas(insertedToken);
    }

    @RequestMapping(value = "/getBookHireForUser/{token}", method = RequestMethod.GET)
    public ResponseEntity<?>  getBookHireForUser(@PathVariable("token") String insertedToken){
        return userSideService.getBookHireForUser(insertedToken);
    }

    @RequestMapping(value = "/getAllBooks/{token}", method = RequestMethod.GET)
    public ResponseEntity<?>  getAllBooks(@PathVariable("token") String insertedToken){
        return userSideService.getAllBooks(insertedToken);
    }
    @RequestMapping(value = "/hireBook/{token}/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<?>  hireBook(@PathVariable("token") String insertedToken, @PathVariable("bookId") String bookId){
        return userSideService.hireBook(insertedToken, bookId);
       // return new ResponseEntity(HttpStatus.OK);
    }
}
