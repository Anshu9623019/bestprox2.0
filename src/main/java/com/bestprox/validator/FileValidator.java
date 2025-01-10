package com.bestprox.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile>{


    public static final long MAX_FILE_SIZE = 1024*1024*2;

    //type


    //height

    //width

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
       

        if(file==null || file.isEmpty()){

            // context.disableDefaultConstraintViolation();
            // context.buildConstraintViolationWithTemplate("File Connnot be empty").addConstraintViolation();
            return true;
        }

        if(file.getSize()>MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
            return false;
        }
        // Resolutation
        // try {
        //     BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        return true;
    }

}
