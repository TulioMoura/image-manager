package com.tdm.imagemanager.DAO.implementations.localFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;

import io.github.cdimascio.dotenv.Dotenv;

import ij.plugin.PlugInInterpreter;

public class imageDaoToLocalFolder implements imageDaoInterface{
    private static String imagesFolder = Dotenv.load().get("LOCAL_IMAGES_FOLDER"); 
    private static String tmpFolder = Dotenv.load().get("TEMPORARY_FOLDER"); 
    public File getImage(String id){
        File image = new File(imagesFolder+id+".png");
        return image;
    }

    public boolean checkIfImageExists(String id){
        File image = new File(imagesFolder+id+".png");
        return image.exists()?true : false;
    }

    public void saveImage(String temporaryPath,String id){
        
            Path definitive = Paths.get(imagesFolder+id+".png");
            Path temporary = Paths.get(temporaryPath);

            try{
                Files.copy(temporary, definitive, StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException exception){
                System.out.println(exception.toString());
            }
        
    }
    public String deleteImage(String id) throws IOException{
        File img = new File(imagesFolder+id+".png");
        if (img.delete()){
            return "Image deleted!";
        }
        else{
            throw new IOException("Cannot delete image.");
        }
    }
}
