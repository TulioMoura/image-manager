package com.tdm.imagemanager.DAO.implementations.sqlite;


import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.ImageDescriptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class imageDescriptorDaoSQLite implements imageDescriptorDaoInterface{

    

    private Connection connect() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
        return connection;
    }

    
    public boolean saveDescriptor(ImageDescriptor imageDescriptor)throws Exception{
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

            PreparedStatement s = c.prepareStatement("INSERT INTO imageDescriptor (uuid, uploadDate,characteristics)"
                    + "VALUES ('"+ imageDescriptor.getId() + "','" + imageDescriptor.getDate().getTime() + "','" +characteristics +"');");
            boolean execute = s.execute();
            return true;
        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    };
    
    public ImageDescriptor getOneDescriptor(String id)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "select * from imageDescriptor where uuid='"+id+"';";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();
            int updatedRows = s.getUpdateCount();
            
            long updatedAt = result.getLong("uploadDate");
            System.out.println(updatedAt);
            System.out.println(new Date(updatedAt));
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
    public ArrayList<ImageDescriptor>getAllDescriptors()throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "select * from imageDescriptor;";
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery();
            //int updatedRows = s.getUpdateCount();
            
            ArrayList<ImageDescriptor> response = new ArrayList<ImageDescriptor>();
            do{
                String id = result.getString("uuid");
                long updatedAt = result.getLong("uploadDate");
                String characteristicsString = result.getString("characteristics");
                String characteristicsArray[] = characteristicsString.split("\n");
                ArrayList<String> characteristicsArrayList = new ArrayList<String>();
                for (String string : characteristicsArray) {
                    characteristicsArrayList.add(string);
                }
                ImageDescriptor img = new ImageDescriptor(id,new Date(updatedAt),characteristicsArrayList);
                response.add(img);
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
    public boolean deleteDescriptor(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "delete from imageDescriptor where uuid='"+id+"';";
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
}
