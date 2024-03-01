package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface imageDescriptorDaoInterface{
    public boolean saveDescriptor(ImageDescriptor imageDescriptor, ArrayList<String> categoriesIds,ArrayList<String> galleriesIds)throws Exception;
    public boolean addDescriptorToCategories(String idDescriptor, ArrayList<String> categoriesIds) throws Exception;
    public boolean addDescriptorToGalleries(String idDescriptor, ArrayList<String> galleriesIds) throws Exception;

    public ArrayList<ImageDescriptor>getAllDescriptors()throws Exception;
    public ArrayList<ImageDescriptor>getDescriptorsByCategory(String id) throws Exception;
    public ArrayList<ImageDescriptor>getDescriptorsByGallery(String id) throws Exception;
    public ImageDescriptor getOneDescriptor(String id)throws Exception;

    public boolean removeDescriptorFromCategory(String idDescriptor, String categoryDescriptor) throws Exception;
    public boolean removeDescriptorFromGallery(String idDescriptor, String galleryDescriptor) throws Exception;

    public boolean deleteDescriptor(String id) throws Exception;
}