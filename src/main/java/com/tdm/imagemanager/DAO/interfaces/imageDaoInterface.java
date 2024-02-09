package com.tdm.imagemanager.DAO.interfaces;
import java.io.File;
public interface imageDaoInterface {
    public File getImage(String id);
    public File saveImage(File img, String id);
    public void deleteImage(String id)throws  Exception;
}
