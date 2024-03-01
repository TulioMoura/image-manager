package com.tdm.imagemanager.DAO.implementations.sqlite;

import java.util.ArrayList;

import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.classes.Gallery;

public class galleriesDaoSQLite implements galleriesDaoInterface {

    public ArrayList<Gallery> getAllGalleries()throws Exception{
        return new ArrayList<Gallery>();
    }
    public Gallery getOneGallery(String id)throws Exception{
        return new Gallery("s", "s");
    }
    public boolean saveGallery(Gallery gallery) throws Exception{
        return false;
    }
    public boolean deleteGallery(String id) throws Exception{
        return false;

    }
}
