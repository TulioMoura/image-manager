package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Gallery;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface galeriesDaoInterface{
    public ArrayList<Gallery> getAllGalleries()throws Exception;
    public Gallery getOneGallery(String id)throws Exception;
    public boolean saveGallery(Gallery gallery) throws Exception;
    public boolean deleteGallery(String id) throws Exception;
}