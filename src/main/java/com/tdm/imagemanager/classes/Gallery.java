package com.tdm.imagemanager.classes;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Exception;
import java.lang.String;

public class Gallery{
    private String uuid;
    private String name;
    private Date dataInclusão;    
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