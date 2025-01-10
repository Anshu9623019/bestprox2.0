package com.bestprox.service;

import java.util.List;
import java.util.Optional;

import com.bestprox.entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User User);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    //add more method related to user id.

}
