package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Gallery;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface galleriesDaoInterface{
    public boolean addGallery(Gallery gallery) throws Exception;
    public boolean updateGallery(Gallery gallery) throws Exception;
    public Gallery getOneGallery(String id)throws Exception;
    public ArrayList<Gallery> getGalleriesByDescriptorId(String id) throws Exception;
    public ArrayList<Gallery> getAllGalleries()throws Exception;
    public boolean removeGallery(String id) throws Exception;
}