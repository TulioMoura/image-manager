package com.tdm.imagemanager.classes.transferObjects.imageCategories;

import java.beans.ConstructorProperties;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class descriptorsCategory {
    @JsonProperty("image")
    private ArrayList<String> images_ids;
    @JsonProperty("category")
    private String category_id;

    public String getCategoryId(){
        return category_id;
    }
    public ArrayList<String> getImagesId(){
        return images_ids;
    }

    @ConstructorProperties("{image,gallery}")
    public descriptorsCategory(ArrayList<String> images, String category){
        this.images_ids = images;
        this.category_id = category;
    }
}
