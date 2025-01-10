package com.bestprox.forms;

import org.springframework.web.multipart.MultipartFile;

import com.bestprox.validator.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address [ example@gmail.com ]")
    private String email;

    @NotBlank(message = "PhoneNumber is Required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean fevorite;
    private String websiteLink;
    private String linkedLink;

    //annotation create karenge or usko validate karenge

    //size

    //resolution

    //
    @ValidFile
    private MultipartFile contactImage;

    private String picture;
}
