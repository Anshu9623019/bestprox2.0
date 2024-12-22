package com.bestprox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bestprox.entities.User;
import com.bestprox.forms.UserForm;
import com.bestprox.helpers.Message;
import com.bestprox.helpers.MessageType;
import com.bestprox.repositoris.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {

    @Autowired
    private UserService UserService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page Handler");
        model.addAttribute("name","Bestprox cool website");
        model.addAttribute("location", "kolkata");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        System.out.println("About Page Loading");
        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        System.out.println("Services Page Loading");
        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {
        System.out.println("Contact page Loading");
        return "contact";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("login page Loading");
        return "login";
    }
    @RequestMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();

        // default data.
        userForm.setName("Anshu");
        
        model.addAttribute("userForm", userForm);

        System.out.println("register page Loading");
        return "register";
    }

    //form processs
    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm,HttpSession session) {
        System.out.println(userForm);

        //TODO :: Validate user form 

        // save to database
        // userForm--> user
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://flowbite.com/images/logo.svg")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://flowbite.com/images/logo.svg");
        
        User savedUser = UserService.saveUser(user);

        System.out.println("Processing Form");


        //message = "Registration Successful"

        Message message = Message.builder().content("Registration Successful").type(MessageType.blue).build();

        session.setAttribute("message",message);

        //add the message : 


        // Redirection login page
        return "redirect:/register";
    }
    
    
    
}
