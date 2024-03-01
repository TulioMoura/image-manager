package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface imageDescriptorDaoInterface{
    public ArrayList<ImageDescriptor>getAllDescriptors()throws Exception;
    public ArrayList<ImageDescriptor>getDescriptorsByCategory(String id) throws Exception;
    public ArrayList<ImageDescriptor>getDescriptorsByGallery(String id) throws Exception;
    public ImageDescriptor getOneDescriptor(String id)throws Exception;
    public boolean saveDescriptor(ImageDescriptor imageDescriptor)throws Exception;
    public boolean deleteDescriptor(String id) throws Exception;
}