package com.tdm.imagemanager.classes.baseApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.lang.Exception;
import java.lang.String;

public class Gallery{
    @JsonProperty("id")
    private String uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    private Date dataInclusão;
    @JsonProperty("images")    
    private ArrayList<String> images;

    public String getId(){
        return uuid;
    }
    public String getName(){
        return name;
    }

    public Date getDate(){
        return this.dataInclusão;
    }

    public ArrayList<String>getImages(){
        return images;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addImage(String id) throws Exception{
        if(images.contains(id)){
            throw new Exception("Image is already on category");
        }
        else{
            images.add(id);
        }
    }

    public void removeImage(String id) throws Exception{
        if(images.contains(id)){
            images.remove(id);
        }
        else{
            throw new Exception("Image isnt on category");
        }
    }

    @ConstructorProperties({"name"})
    public Gallery(String name){
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.dataInclusão = new Date();
        this.images= new ArrayList<String>();
    }

    public Gallery(String nome, String id){
        this.uuid = id;
        this.setName(nome);
        this.dataInclusão = new Date(); 
        this.images = new ArrayList<String>();
    }

    public Gallery(String nome, String id, long created_at){
        this.uuid = id;
        this.setName(nome);
        this.dataInclusão = new Date(created_at); 
        this.images = new ArrayList<String>();
    }
}