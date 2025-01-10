package com.bestprox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestprox.entities.Contact;
import com.bestprox.service.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactService;
    //get Contact
    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId){
        return contactService.getById(contactId);
    }

    
}
