package com.bestprox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestprox.entities.Contact;
import com.bestprox.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public interface ContactService {

    //save Contact
    Contact save(Contact contact);

    //update Contact
    Contact update(Contact contact);

    // Get Contact
    List<Contact> getAll();

    //get contact by id
    Contact getById(String id);


    // delete Conatct
    void delete(String name);

    //Search by name
    Page<Contact> searchByName(String nameKeyword,int page, int size, String sortBy, String order,User user);

    //Search By phone Number
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int page, int size, String sortBy, String order,User user);

    // Search by Email
    Page<Contact> searchByEmail(String emailKeyword,int page , int size, String sortBy, String order,User user);

    //get Contact by user id
    List<Contact> getByUserId(String userId);

    //
    Page<Contact> getByUser(User user,int page, int size,String sortBy,String direction);


}
