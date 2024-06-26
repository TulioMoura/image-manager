package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.baseApplication.Gallery;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;

import java.lang.String;
public interface galleriesDaoInterface{
    public boolean addGallery(Gallery gallery) throws Exception;
    public boolean updateGallery(Gallery gallery) throws Exception;
    public Gallery getOneGallery(String id)throws Exception;
    public ArrayList<String> getGalleriesByDescriptorId(String id) throws Exception;
    public ArrayList<String> getAllGalleries()throws Exception;
    public boolean removeGallery(String id) throws Exception;
}