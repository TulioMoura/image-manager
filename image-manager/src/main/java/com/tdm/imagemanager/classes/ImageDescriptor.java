package com.tdm.imagemanager.classes;

import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.awt.Image;
import java.awt.Image.*;
import java.awt.image.*;

public class ImageDescriptor{
    private String uuid;
    private Date uploadDate;
    private ArrayList<String> characteristics;
    private Image thumbnail;
    private Image image;

    public String getId(){
        return uuid;
    }
    public Date getDate(){
        return uploadDate;
    }
    public ArrayList<String> getCharacteristics(){
        return characteristics;
    }
    public Image getThumbnail(){
        return thumbnail;
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

    public void setThumbnail(Image thumbnail){
        this.thumbnail = thumbnail;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public ImageDescriptor(String uuid,ArrayList<String> characteristics, Image thumbnail){
        this.characteristics = characteristics;
        this.thumbnail = thumbnail;
        this.uuid = uuid;
        this.uploadDate = new Date();
    }
}