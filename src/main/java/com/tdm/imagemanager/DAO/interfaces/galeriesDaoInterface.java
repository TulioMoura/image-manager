package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Gallery;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface galeriesDaoInterface{
    public ArrayList<Gallery> getAllGalleries();
    public Gallery getOneGallery(String id);
    public Gallery saveGallery(Gallery gallery);
    public void deleteGallery(String id);
}