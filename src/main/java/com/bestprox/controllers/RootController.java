package com.bestprox.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bestprox.entities.User;
import com.bestprox.helpers.Helper;
import com.bestprox.service.UserService;

@ControllerAdvice
public class RootController {
    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){

        //Adding logged in information to the Model
        if(authentication==null){
            return;
        }

        System.out.println("Adding loged information to the model");
        
         // Principal principal = (Principal)authentication.getPrincipal();
         String username = Helper.getEmailLoggedinUser(authentication);
         logger.info("User logged in {}",username);
         // get data from data base
         User user = userService.getUserByEmail(username);
         System.out.println(user);
         System.out.println(user.getName());
         System.out.println(user.getEmail());
         model.addAttribute("loggedInUser", user); 
    }
}
