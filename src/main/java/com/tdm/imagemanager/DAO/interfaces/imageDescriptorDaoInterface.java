package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
interface imgageDescriptorDaoInterface{
    public ArrayList<ImageDescriptor>getAllDescriptors();
    public ImageDescriptor getOneDescriptor(String id);
    public ImageDescriptor saveDescriptor(ImageDescriptor imageDescriptor);
    public void deleteDescriptor(String id) throws Exception;
}