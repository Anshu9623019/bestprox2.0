package com.bestprox.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bestprox.helpers.AppConstants;
import com.bestprox.service.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageSeprviceImpl  implements ImageService{

    private Cloudinary cloudinary;

    

    public ImageSeprviceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }



    @Override
    public String uploadImage(MultipartFile contactImage,String filename) {
        
        // Code likhna hai jo image upload kr rhah ho

        // String filename = UUID.randomUUID().toString();

        try {
            byte[] data =  new byte[contactImage.getInputStream().available()];

            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename

            ));

            return this.getUrlFromPublicId(filename);
        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }
        // and return kr raha hoga
    }



    @Override
    public String getUrlFromPublicId(String publicId) {
        
        return cloudinary.url().transformation(new Transformation<>()
        .width(AppConstants.CONTACT_IMAGE_WIDTH)
        .height(AppConstants.CONTACT_IMAGE_HEIGHT)
        .crop(AppConstants.CONTACT_IMAGE_CROP)
        ).generate(publicId);
    }

    

    
}
