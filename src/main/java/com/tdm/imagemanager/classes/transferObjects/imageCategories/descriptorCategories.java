package com.tdm.imagemanager.classes.transferObjects.imageCategories;

import java.beans.ConstructorProperties;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class descriptorCategories {
    
    @JsonProperty("image")
    private String image_id;
    @JsonProperty("category")
    private ArrayList<String> categories_ids;

    public ArrayList<String> getCategoryId(){
        return categories_ids;
    }
    public String getImageId(){
        return image_id;
    }

    @ConstructorProperties("{image,gallery}")
    public descriptorCategories(String image, ArrayList<String> category){
        this.image_id = image;
        this.categories_ids = category;
    }
}
