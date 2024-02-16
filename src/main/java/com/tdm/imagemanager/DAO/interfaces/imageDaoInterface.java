package com.tdm.imagemanager.DAO.interfaces;
import java.io.File;
import java.io.IOException;
public interface imageDaoInterface {
    public File getImage(String id);
    public void saveImage(String temporaryPath, String id);
    public String deleteImage(String id) throws  IOException;
}
