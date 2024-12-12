package com.bestprox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

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
    
}
