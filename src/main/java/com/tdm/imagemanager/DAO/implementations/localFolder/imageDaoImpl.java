package com.tdm.imagemanager.DAO.implementations;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;

import ij.plugin.PlugInInterpreter;

public class imageDaoImpl implements imageDaoInterface{
    public File getImage(String id){
        File image = new File("tmp/"+id+".png");
        System.out.println(image.exists());
        System.out.println(image.toString());
        return image;
    }
    public void saveImage(String temporaryPath,String id){
        
            Path definitive = Paths.get("tmp/"+id+".png");
            Path temporary = Paths.get(temporaryPath);

            try{
                Files.copy(temporary, definitive, StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException exception){
                System.out.println(exception.toString());
            }
        
    }
    public String deleteImage(String id) throws IOException{
        File img = new File("/tmp/"+id+".png");
        if (img.delete()){
            return "Image Deleted!";
        }
        else{
            throw new IOException("Cannot delete image.");
        }
    }
}
