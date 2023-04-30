package com.palavecinofranco.usersystem.controllers;

import com.palavecinofranco.usersystem.dao.UserDao;
import com.palavecinofranco.usersystem.models.User;
import com.palavecinofranco.usersystem.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id){
        User user = new User();
        user.setId(1);
        user.setName("Franco");
        user.setLastname("Palavecino");
        user.setEmail("francopalavecinoagus@gmail.com");
        user.setPassword("1234");
        user.setCellphone("1168320194");
        return user;
    }
    //get the id user with the token
    private boolean verifyToken(String token){
        String idUser = jwtUtil.getKey(token);
        return idUser != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if(!verifyToken(token)){
            return null;
        }
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable long id ){
        if(!verifyToken(token)){
            return;
        }
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPassword = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hashPassword);
        userDao.createUser(user);
    }

}
