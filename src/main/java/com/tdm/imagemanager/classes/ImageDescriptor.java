package com.tdm.imagemanager.classes;

import java.util.ArrayList;
import java.util.Date;
import java.lang.String;

public class ImageDescriptor{
    private String uuid;
    private Date uploadDate;
    private ArrayList<String> characteristics;

    public String getId(){
        return uuid;
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



    public ImageDescriptor(String uuid,ArrayList<String> characteristics){
        this.characteristics = characteristics;
        this.uuid = uuid;
        this.uploadDate = new Date();
    }
}