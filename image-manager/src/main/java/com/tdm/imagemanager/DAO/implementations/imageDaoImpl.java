package com.tdm.imagemanager.DAO.implementations;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;

import ij.plugin.PlugInInterpreter;

public class imageDaoImpl implements imageDaoInterface{
    public File getImage(String id){
        File image = new File("/tmp/"+id+".png");
        System.out.println(image.exists());
        System.out.println(image.toString());
        return image;
    }
    public File saveImage(File img,String id){
        try{
            System.out.println("---------saveimagemthd");
            System.out.println(img.length());
            System.out.println(img.getName());
            System.out.println(img.getCanonicalPath());
            img.createNewFile();
            return img;
        }
        catch (IOException iex){
            System.out.println(iex);
            return img;
        }
    }
    public void deleteImage(String id)throws Exception{
        File img = new File("/tmp/"+id);
        if (img.delete()){
            return;
        }
        else{
            throw new Exception("cannot delete file");
        }
    }
}
