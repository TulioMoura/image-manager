package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;

public interface imageDescriptorDaoInterface{
    //GENERAL USAGE (ADD AND REMOVE)
    public boolean saveDescriptor(ImageDescriptor imageDescriptor, ArrayList<String> categoriesIds,ArrayList<String> galleriesIds)throws Exception;
    public boolean removeDescriptor(String id) throws Exception;
    
    //Add to categories
    public boolean addDescriptorToCategories(String descriptorId, ArrayList<String> categoriesIds) throws Exception;
    public boolean addDescriptorToCategory(String descriptorId, String idCategory) throws Exception;
    public boolean addDescriptorsToCategory(ArrayList<String> descriptorsIds, String categoryId)throws Exception;

    //Remove from Categories
    public boolean removeDescriptorFromCategory(String descriptorId, String categoryId) throws Exception;
    public boolean removeDescriptorsFromCategory(ArrayList<String> descriptorsIds, String categoryId) throws Exception;
    public boolean removeDescriptorFromCategories(String descriptorId, ArrayList<String> categoriesIds) throws Exception; 

    //Add to Galleries
    public boolean addDescriptorToGalleries(String descriptorId, ArrayList<String> galleriesIds) throws Exception;
    public boolean addDescriptorToGallery(String descriptorId, String galleryId) throws Exception;
    public boolean addDescriptorsToGallery(ArrayList<String> descriptorsIds, String galleryId) throws Exception;

    //Remove from Galleries
    public boolean removeDescriptorFromGallery(String descriptorId, String galleryId) throws Exception;
    public boolean removeDescriptorsFromGallery(ArrayList<String> descriptorsIds, String galleryId) throws Exception;
    public boolean removeDescriptorFromGalleries(String descriptorId, ArrayList<String> galleriesIds) throws Exception;

    //Get resource from database
    //public boolean checkIfDescriptorWasUploaded(String id) throws Exception;
    public ImageDescriptor getDescriptor(String id)throws Exception;
    public ArrayList<ImageDescriptor>getAllDescriptors()throws Exception;

    //Find resources on database
    public ArrayList<String>findByCategory(String id) throws Exception;
    public ArrayList<String>findByGallery(String id) throws Exception;    
}