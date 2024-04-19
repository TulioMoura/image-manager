package com.tdm.imagemanager.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.lang.String;

public class ImageDescriptor{
    @JsonProperty("id")
    private String uuid;
    @JsonProperty("uploadDate")
    private Date uploadDate;
    @JsonProperty("characteristics")
    private ArrayList<String> characteristics;

    
    public String getId(){
        return uuid;
    }

    public void setId(String id){
        this.uuid= id;
    }

    public Date getDate(){
        return uploadDate;
    }
    public ArrayList<String> getCharacteristics(){
        return characteristics;
    }
    
    public void addCharacteristic(String charcateristic){
        characteristics.add(charcateristic);
    }

    public void removeCharacteristic(int index){
        characteristics.remove(index);
    }

    public void removeAllCharacteristics(){
        characteristics = new ArrayList<String>();
    }


    //@ConstructorProperties({"uuid","characteristics"})
    public ImageDescriptor(String uuid,ArrayList<String> characteristics){
        this.characteristics = characteristics;
        this.uuid = uuid;
        this.uploadDate = new Date();
    }

    @ConstructorProperties({"characteristics"})
    public ImageDescriptor(ArrayList<String> characteristics){
        this.characteristics = characteristics;
        this.uuid = UUID.randomUUID().toString();
        this.uploadDate = new Date();
    }

    public ImageDescriptor(String uuid,Date uploadDate,ArrayList<String> characteristics){
        this.characteristics = characteristics;
        this.uuid = uuid;
        this.uploadDate = uploadDate;
    }

}