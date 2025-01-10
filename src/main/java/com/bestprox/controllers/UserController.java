package com.bestprox.controllers;




import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bestprox.entities.User;
import com.bestprox.helpers.Helper;
import com.bestprox.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/dashboard")
    public String userDashboard() {

        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Model model ,Authentication authentication) {

       // Principal principal = (Principal)authentication.getPrincipal();
        String username = Helper.getEmailLoggedinUser(authentication);


        // get data from data base

        User user = userService.getUserByEmail(username);

        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);

        logger.info("User Logged in " +username);
        System.out.println("user profile");
        return "user/profile";
    }


    

}
