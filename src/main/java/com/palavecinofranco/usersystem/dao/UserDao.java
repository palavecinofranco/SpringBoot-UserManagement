package com.palavecinofranco.usersystem.dao;

import com.palavecinofranco.usersystem.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void deleteUser(long id);

    void createUser(User user);

    User getUserByCredentials(User user);
}
