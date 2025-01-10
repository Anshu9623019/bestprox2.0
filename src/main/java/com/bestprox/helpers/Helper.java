package com.bestprox.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailLoggedinUser(Authentication authentication) {


        //Principal principal = (Principal) authentication.getPrincipal();
        // agr email or password se login kia h to user kaise nikalege

        if (authentication instanceof OAuth2AuthenticationToken) {
            // Sign with google
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";
            // Google
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("geeting email from google");
                username = oauth2User.getAttribute("email").toString();
            // Github
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("geeting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() 
                : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return username;
        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

    }



    public static String getLinkForEmailVerification(String emailToken){
        String link = "http://localhost:8081/auth/verify-email?token=" + emailToken;
        return link;
    }
}

