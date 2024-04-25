package com.tdm.imagemanager.DAO.implementations.SQL;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


import io.github.cdimascio.dotenv.Dotenv;

import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.Gallery;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;
public class galleriesDaoSQL implements galleriesDaoInterface {

    private Connection connect() throws Exception{
        Dotenv environment = Dotenv.load();
        Connection connection = DriverManager.getConnection(environment.get("DATABASE_URL"));
        return connection;
    }

    public boolean addGallery(Gallery gallery) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("INSERT INTO gallery (id, name, created_at)"
                    + "VALUES ('"+ gallery.getId() + "','" + gallery.getName() + "','" +gallery.getDate().getTime()+"');");
            s.execute();
            
            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            System.out.println("closed_connadd");
            c.close();
        }
    }
    public boolean updateGallery(Gallery gallery) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("UPDATE gallery set "
                    +"name = '"+gallery.getName()+"' WHERE id='"+gallery.getId()+"';");
            s.execute();

            imageDescriptorDaoInterface imageDescriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> databaseImageList = imageDescriptorDao.findByGallery(gallery.getId());
            ArrayList<String> updatedImageList = gallery.getImages();
            ArrayList<String> imagesToAddToGallery = new ArrayList<String>();

            for(String image : databaseImageList){
                if(!updatedImageList.contains(image)){ 
                    imageDescriptorDao.removeDescriptorFromGallery(image, gallery.getId());
                }
            }
            for(String image : updatedImageList){
                if(!databaseImageList.contains(image)){
                    imageDescriptorDao.addDescriptorToGalleries(image, imagesToAddToGallery);
                }
            }
            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            
            System.out.println("closed_connupdt");
            c.close();
        }
    }

    public Gallery getOneGallery(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            String query = "select * from gallery where id='"+id+"';";
            PreparedStatement s = c.prepareStatement(query);
            System.out.println(query);
            ResultSet result = s.executeQuery();
            int updatedRows = s.getUpdateCount();
            System.out.println(updatedRows);
           

            String name = result.getString("name");
            System.out.println(name);
            long created_at = result.getLong("created_at");
            Gallery gallery =  new Gallery(name, id, created_at);
            imageDescriptorDaoInterface descriptorDao = new imageDescriptorDaoSQL();
            ArrayList<String> descriptor_ids  = descriptorDao.findByGallery(id);
            for (String string : descriptor_ids) {
                gallery.addImage(string);
            }

            return gallery;
            
            
            
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            
            System.out.println("closed_conngetone");
            c.close();
        }
    }

    public ArrayList<String> getGalleriesByDescriptorId(String descriptor_id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT id FROM gallery, img_descriptor,image_gallery where "
            +"image_gallery.image_id =="+"'"+descriptor_id+"' AND gallery.id== image_gallery.gallery_id AND img_descriptor.uuid ==image_gallery.image_id");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<String> galleryList = new ArrayList<String>();
            
            while(result.next()){
            String id = result.getString("id");
            galleryList.add(id);
            };         
            return galleryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            
            System.out.println("closed_conngetbydescid");
            c.close();
        }
    }

    public ArrayList<String> getAllGalleries()throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT id FROM gallery");
            s.execute();
            ResultSet result = s.getResultSet();
            ArrayList<String> galleryList = new ArrayList<String>();
            
            while(result.next()){
            String id = result.getString("id");
            galleryList.add(id);
            };         
            return galleryList;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            c.close();
        }
    }
        
    public boolean removeGallery(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("SELECT * FROM gallery,image_gallery where "
            +"image_gallery.gallery_id =="+"'"+id+"' AND gallery.id == image_gallery.gallery_id");
            s.execute();
            return true;
            
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
        finally{
            //fecha a conexão com o bd
            
            System.out.println("closed_connremgal");
            c.close();
        }

    }
}
