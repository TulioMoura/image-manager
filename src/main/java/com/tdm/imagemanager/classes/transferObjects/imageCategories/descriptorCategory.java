package com.tdm.imagemanager.classes.transferObjects.imageCategories;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

public class descriptorCategory {

    @JsonProperty("image")
    private String image_id;
    @JsonProperty("category")
    private String category_id;

    public String getCategoryId(){
        return category_id;
    }
    public String getImageId(){
        return image_id;
    }

    @ConstructorProperties("{image,gallery}")
    public descriptorCategory(String image, String category){
        this.image_id = image;
        this.category_id = category;
    }
}