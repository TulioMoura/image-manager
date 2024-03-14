package com.tdm.imagemanager.DAO.implementations.sqlite;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.Gallery;
import com.tdm.imagemanager.classes.ImageDescriptor;

public class galleriesDaoSQLite implements galleriesDaoInterface {

    private Connection connect() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
        return connection;
    }

    public boolean saveGallery(Gallery gallery) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("INSERT INTO galleries (id, name, created_at)"
                    + "VALUES ('"+ gallery.getId() + "','" + gallery.getName() + "','" +gallery.getDate().getTime()+"');");
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

    public Gallery getOneGallery(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM galleries where "
            +"id ='"+id+"';");
            s.execute();
            ResultSet result = s.getResultSet();
            String name = result.getString("name");
            long created_at = result.getLong("created_at");
            Gallery gallery =  new Gallery(name, id, created_at);
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQLite();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(id);
            for (String string : descriptor_ids) {
                gallery.addImage(id);
            }
            return gallery;

        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }

    public ArrayList<Gallery> getGalleriesByDescriptorId(String descriptor_id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM galleries, imageDescriptor,imageGallery where "
            +"imageGallery.image_id =="+"'"+descriptor_id+"' AND galleries.id== imageGallery.gallery_id");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<Gallery> galleryList = new ArrayList<Gallery>();
            
            while(result.next()){
                String name = result.getString("name");
            long created_at = result.getLong("created_at");
            String id = result.getString("id");
            Gallery gallery =  new Gallery(name, id, created_at);
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQLite();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(id);
            for (String string : descriptor_ids) {
                gallery.addImage(id);
            }
            System.out.println(gallery.getId());
            galleryList.add(gallery);
            };         
            return galleryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }

    public ArrayList<Gallery> getAllGalleries()throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM galleries");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<Gallery> galleryList = new ArrayList<Gallery>();
            
            while(result.next()){
            System.out.println("itneeeee");
            String id = result.getString("id");
            String name = result.getString("name");
            long created_at = result.getLong("created_at");
            Gallery gallery =  new Gallery(name, id, created_at);
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQLite();
            ArrayList<String> descriptor_ids  = descriptorDao.findByCategory(id);
            for (String string : descriptor_ids) {
                gallery.addImage(string);
            }
            galleryList.add(gallery);
            };         
            return galleryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
        
    public boolean deleteGallery(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM galleries,imageGallery where "
            +"imageGallery.gallery_id =="+"'"+id+"' AND galleries.id == imageGallery.gallery_id");
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
