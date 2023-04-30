package com.palavecinofranco.usersystem.controllers;

import com.palavecinofranco.usersystem.dao.UserDao;
import com.palavecinofranco.usersystem.models.User;
import com.palavecinofranco.usersystem.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User userLogin = userDao.getUserByCredentials(user);
        if(userLogin!=null){
            //create the token
            String token = jwtUtil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());
            return token;
        }
        return "FAIL";
    }

}
