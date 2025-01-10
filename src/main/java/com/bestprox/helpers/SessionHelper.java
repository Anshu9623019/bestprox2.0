package com.bestprox.helpers;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession ;

@Component
public class SessionHelper {


    public static void removeMessage(){

    try {
        System.out.println("Removing message from session");
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");
    } catch (Exception e) {
       System.out.println("error in sesssion handler"+e);
       e.printStackTrace();
    }

    }
}
