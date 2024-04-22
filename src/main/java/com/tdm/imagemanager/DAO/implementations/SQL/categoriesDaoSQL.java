package com.tdm.imagemanager.DAO.implementations.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import io.github.cdimascio.dotenv.Dotenv;

import com.tdm.imagemanager.DAO.interfaces.categoriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.Category;
import com.tdm.imagemanager.classes.baseApplication.Gallery;

public class categoriesDaoSQL implements categoriesDaoInterface {
private Connection connect() throws Exception{
    Dotenv environment = Dotenv.load();
        Connection connection = DriverManager.getConnection(environment.get("DATABASE_URL"));
        return connection;
    }

    public boolean addCategory(Category category) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("INSERT INTO category (name, created_at)"
                    + "VALUES ('" + category.getName() + "','" +category.getDate().getTime()+"');");
            s.execute();
            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
    public boolean updateCategory(Category category) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            imageDescriptorDaoInterface imageDescriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> databaseImageList = imageDescriptorDao.findByCategory(category.getName());
            ArrayList<String> updatedImageList = category.getImages();
            ArrayList<String> imagesToAddToCategory = new ArrayList<String>();

            for(String image : databaseImageList){
                if(!updatedImageList.contains(image)){ 
                    imageDescriptorDao.removeDescriptorFromGallery(image, category.getName());
                }
            }
            for(String image : updatedImageList){
                if(!databaseImageList.contains(image)){
                    imageDescriptorDao.addDescriptorToGalleries(image, imagesToAddToCategory);
                }
            }




            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }

    public Category getOneCategory(String name)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM category where "
            +"name ='"+name+"';");
            s.execute();
            ResultSet result = s.getResultSet();
            long createdAt = result.getLong("created_at");
            Category category =  new Category(name, new Date(createdAt));
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(name);
            for (String string : descriptor_ids) {
                category.addImage(string);
            }
            return category;

        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }

    public ArrayList<Category> getCategoriesByDescriptorId(String descriptor_id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM category, img_descriptor,image_category where "
            +"image_category.image_id =="+"'"+descriptor_id+"' AND category.name== image_category.category_name AND img_descriptor.uuid == image_category.image_id");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<Category> categoryList = new ArrayList<Category>();
            
            while(result.next()){
                String name = result.getString("name");
            long created_at = result.getLong("created_at");
            Category category =  new Category(name, new Date(created_at));
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(name);
            for (String string : descriptor_ids) {
                category.addImage(string);
            }
            categoryList.add(category);
            };         
            return categoryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }

    public ArrayList<Category> getAllCategories()throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM category");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<Category> categoryList = new ArrayList<Category>();
            
            while(result.next()){
            String name = result.getString("name");
            long createdAt = result.getLong("createdAt");
            Category category =  new Category(name,new Date(createdAt));
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(name);
            for (String string : descriptor_ids) {
                category.addImage(string);
            }
            categoryList.add(category);
            };         
            return categoryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
        
    public boolean removeCategory(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM category,image_category where "
            +"image_category.category =="+"'"+id+"' AND category.name == image_category.category_name");
            s.execute();
            return true;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }

    }
}