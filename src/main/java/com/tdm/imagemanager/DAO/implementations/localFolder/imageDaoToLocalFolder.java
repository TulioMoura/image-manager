package com.tdm.imagemanager.DAO.implementations.localFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;

import ij.plugin.PlugInInterpreter;

public class imageDaoToLocalFolder implements imageDaoInterface{
    public File getImage(String id){
        File image = new File("images/"+id+".png");
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
        File img = new File("tmp/"+id+".png");
        if (img.delete()){
            return "Image deleted!";
        }
        else{
            throw new IOException("Cannot delete image.");
        }
    }
}
