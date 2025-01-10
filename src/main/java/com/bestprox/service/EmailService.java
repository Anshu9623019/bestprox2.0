package com.bestprox.service;

import org.springframework.stereotype.Service;


public interface EmailService {
    void sendEmail(String to, String subject, String body);

    // void 
   void sendEmailWithHtml();
   //
   void sendEmailWithAttachement();
}
