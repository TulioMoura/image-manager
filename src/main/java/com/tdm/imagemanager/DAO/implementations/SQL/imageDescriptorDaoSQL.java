package com.tdm.imagemanager.DAO.implementations.SQL;


import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import io.github.cdimascio.dotenv.Dotenv;

public class imageDescriptorDaoSQL implements imageDescriptorDaoInterface{

    

    private Connection connect() throws Exception{
        Dotenv environment = Dotenv.load();
        Connection connection = DriverManager.getConnection(environment.get(System.getProperty("TEST").equals("TRUE") ? "TESTING_DATABASE_URL":"DATABASE_URL"));
        return connection;
    }

    
    public boolean saveDescriptor(ImageDescriptor imageDescriptor, ArrayList<String> categoriesIds, ArrayList<String> galleriesIds)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            ArrayList<String> characteristicsArrayList = imageDescriptor.getCharacteristics();
            //System.out.println(characteristicsArrayList.get(0)+","+characteristicsArrayList.get(1));
            String characteristics = "";
            for (String string : characteristicsArrayList) {
                if(characteristics == ""){
                    characteristics = string;
                }
                else{
                    characteristics = characteristics+"\n"+string;
                }
                
            }
            //System.out.println(characteristics);

            PreparedStatement s = c.prepareStatement("INSERT INTO img_descriptor (uuid, uploadDate,characteristics)"
                    + "VALUES ('"+ imageDescriptor.getId() + "','" + imageDescriptor.getDate().getTime() + "','" +characteristics +"');");
            boolean execute = s.execute();

            addDescriptorToCategories(imageDescriptor.getId(),categoriesIds );
            addDescriptorToGalleries(imageDescriptor.getId(), galleriesIds);
            return true;
        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    };
    public boolean removeDescriptor(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "delete from img_descriptor where uuid='"+id+"';";
            PreparedStatement s = c.prepareStatement(query);
            boolean res = s.execute();
            int updatedRows = s.getUpdateCount();
            s.close();
            if(updatedRows == 1 ){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    };

    public boolean addDescriptorToCategory(String descriptorId, String categoryId) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
              
            
            //System.out.println(characteristics);

            PreparedStatement s = c.prepareStatement("INSERT INTO image_category (category_name, image_id)"
                    + "VALUES ('"+ categoryId + "','" + descriptorId + "');");
            boolean execute = s.execute();
            //System.out.println("desc: "+descriptorId+"  cat:"+categoryId);

            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());         
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
    public boolean addDescriptorToCategories(String descriptorId, ArrayList<String> categoriesIds)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String category:categoriesIds){
                addDescriptorToCategory(descriptorId, category);
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
    public boolean addDescriptorsToCategory(ArrayList<String> descriptorsIds, String categoryId)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String descriptor:descriptorsIds){
                addDescriptorToCategory(descriptor, categoryId);
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
    public boolean removeDescriptorFromCategory(String descriptorId, String categoryId)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
              
            
            //System.out.println(characteristics);

            PreparedStatement s = c.prepareStatement("delete from image_category where "
                    + "category_name == '"+ categoryId + "'and image_id=='" + descriptorId + "';");
            boolean execute = s.execute();

            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());         
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
    public boolean removeDescriptorsFromCategory(ArrayList<String> descriptorsIds, String categoryId) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String descriptor:descriptorsIds){
                removeDescriptorFromCategory(descriptor, categoryId);
                
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
    public boolean removeDescriptorFromCategories(String descriptorId, ArrayList<String> categoriesIds) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String category:categoriesIds){
                removeDescriptorFromCategory(descriptorId, category);
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
    
    
    
    //not implemented
    public boolean addDescriptorToGallery(String descriptorId, String galleryId) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{     
            PreparedStatement s = c.prepareStatement("INSERT INTO image_gallery (gallery_id, image_id)"
                    + "VALUES ('"+ galleryId + "','" + descriptorId + "');");
            boolean execute = s.execute();

            return true;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());         
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
    //not implemented
    public boolean addDescriptorToGalleries(String idDescriptor, ArrayList<String> galleriesIds) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String gallery:galleriesIds){
                addDescriptorToGallery(idDescriptor, gallery);
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
    //not implemented
    public boolean addDescriptorsToGallery(ArrayList<String> descriptorsIds, String galleryId) throws Exception {
        Connection c = connect(); //abre a conexão com o bd
        try{
            for(String descriptor:descriptorsIds){
                addDescriptorToGallery(descriptor, galleryId);
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
    //not implemented
    public boolean removeDescriptorFromGallery(String descriptorId, String galleryId)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
              
            
            //System.out.println(characteristics);

            PreparedStatement s = c.prepareStatement("delete from image_gallery where "
                    + "gallery_id == '"+ galleryId + "'and image_id =='" + descriptorId + "';");
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
    //not implemented
    public boolean removeDescriptorsFromGallery(ArrayList<String> descriptorsIds, String galleryId) throws Exception{
        for(String descriptor:descriptorsIds){
            removeDescriptorFromGallery(descriptor, galleryId);
        }
        return true;
    }
    //not implemented
    public boolean removeDescriptorFromGalleries(String descriptorId, ArrayList<String> galleriesIds) throws Exception{
        for(String gallery:galleriesIds){
            removeDescriptorFromGallery(descriptorId,gallery);
        }
        return true;
    }
    
    //public boolean checkIfDescriptorWasUploaded(String id) throws Exception;
    
    public ImageDescriptor getDescriptor(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "select * from img_descriptor where uuid='"+id+"';";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();
            int updatedRows = s.getUpdateCount();
            
            long updatedAt = result.getLong("uploadDate");
            //System.out.println(updatedAt);
            //System.out.println(new Date(updatedAt));
            String characteristicsString = result.getString("characteristics");
            //System.out.println(characteristicsString);
            s.close();

            String characteristicsArray[] = characteristicsString.split("\n");
            ArrayList<String> characteristicsArrayList = new ArrayList<String>();

            for (String string : characteristicsArray) {
                characteristicsArrayList.add(string);
            }

            ImageDescriptor img = new ImageDescriptor(id,new Date(updatedAt),characteristicsArrayList);
            return img;
            

        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    
    };
    public ArrayList<String>getAllDescriptors()throws Exception{

        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "select uuid from img_descriptor;";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();
            //int updatedRows = s.getUpdateCount();
            
            ArrayList<String> response = new ArrayList<String>();
            do{
                String id = result.getString("uuid");
                response.add(id);
                result.next();
            } while(result.next());
            s.close();
            return response;
            

        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }

    };
    
    public ArrayList<String> findByCategory(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            String query = "select uuid from img_descriptor, image_category where image_category.category_name =='"+
                            id+"' AND image_category.image_id == img_descriptor.uuid;";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();            
            ArrayList<String> response = new ArrayList<String>();
            while(result.next()){
                System.out.println(result +" result");
                String row_id = result.getString("uuid");
                response.add(row_id);
                //result.next();
            } ;
            s.close();
            return response;
            

        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
    public ArrayList<String> findByGallery(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            String query = "select img_descriptor.uuid from img_descriptor, image_gallery where image_gallery.gallery_id ='"+
                            id+"' AND image_gallery.image_id = img_descriptor.uuid;";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();            
            ArrayList<String> response = new ArrayList<String>();
            //result.next();
            while(result.next()){
                String row_id = result.getString("uuid");
                response.add(row_id);
            } ;
            s.close();
            return response;
            

        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());          
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    }
         
}
