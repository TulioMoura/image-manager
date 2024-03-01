package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Category;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface categoriesDaoInterface{
    public ArrayList<Category>getAllCategories() throws Exception;
    public ArrayList<Category>getCategoriesByImageId() throws Exception;
    public Category getOneCategory(String name) throws Exception;
    public Category saveDescriptor(Category category) throws Exception;
    public void deleteDescriptor(String nome) throws Exception;
}