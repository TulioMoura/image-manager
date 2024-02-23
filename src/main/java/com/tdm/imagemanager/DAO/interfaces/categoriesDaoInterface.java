package com.tdm.imagemanager.DAO.interfaces;
import java.util.ArrayList;

import com.tdm.imagemanager.classes.Category;
import com.tdm.imagemanager.classes.ImageDescriptor;
import java.lang.String;
public interface categoriesDaoInterface{
    public ArrayList<Category>getAllCategories();
    public Category getOneCategory(String name);
    public Category saveDescriptor(Category category);
    public void deleteDescriptor(String nome) throws Exception;
}