package com.tdm.imagemanager.classes.transferObjects.ImageGalleries;

import java.beans.ConstructorProperties;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonProperty;

public class descriptorGallery {
    @JsonProperty("image")
    private String image_id;
    @JsonProperty("gallery")
    private String gallery_id;

    public String getGalleryId(){
        return gallery_id;
    }
    public String getImageId(){
        return image_id;
    }

    @ConstructorProperties("{image,gallery}")
    public descriptorGallery(String image, String gallery){
        this.image_id = image;
        this.gallery_id = gallery;
    }
}
