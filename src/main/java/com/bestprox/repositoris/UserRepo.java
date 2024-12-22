package com.bestprox.repositoris;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestprox.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    //extra method db relatedoperation
    //cuatomquery method 
    // cuatom finder method

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
}
