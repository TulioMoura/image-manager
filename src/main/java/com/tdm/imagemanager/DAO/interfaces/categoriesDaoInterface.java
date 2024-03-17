package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Category;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface categoriesDaoInterface{
    public Category addCategory(Category category) throws Exception;
    public boolean updateCategory(Category category) throws Exception;
    public Category getOneCategory(String name) throws Exception;
    public ArrayList<Category>getCategoriesByDescriptorId() throws Exception;
    public ArrayList<Category>getAllCategories() throws Exception;
    public void removeCategory(String nome) throws Exception;
}