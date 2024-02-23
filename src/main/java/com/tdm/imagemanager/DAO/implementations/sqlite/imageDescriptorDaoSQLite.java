package com.tdm.imagemanager.DAO.implementations;


import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.ImageDescriptor;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class imageDescriptorDaoSQLite implements imageDescriptorDaoInterface{

    private boolean sync(Connection c) throws Exception{
        //sincroniza a tabela do banco de dados, ou seja,verifica se a tabela e existe
        // em caso negativo cria a tabela
        try{
            PreparedStatement s = c.prepareStatement(
            "CREATE TABLE IF NOT EXISTS imageDescriptor ("+
	        "uuid TEXT(36) NOT NULL,"+
	        "uploadDate INTEGER NOT NULL,"+
	        "characteristics TEXT(256) NOT NULL,"+
	        "CONSTRAINT imageDescriptor_pk PRIMARY KEY (uuid));");

            
            boolean execute = s.execute();
            return execute;
        } catch (SQLException ex) {
            throw new Exception("sql exception: " + ex.getMessage());
        }
               
    }

    private Connection connect() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
        sync(connection);
        return connection;
    }

    
    public boolean saveDescriptor(ImageDescriptor imageDescriptor)throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{
            PreparedStatement s = c.prepareStatement("INSERT INTO imageDescriptor (uuid, uploadDate,characteristics)"
                    + "VALUES ('"+ imageDescriptor.getId() + "','" + imageDescriptor.getDate() + "','" +imageDescriptor.getCharacteristics().toString() +"');");
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
        System.out.println("not implemented");
        return new ImageDescriptor("asdf", new ArrayList<String>());
    };
    public ArrayList<ImageDescriptor>getAllDescriptors()throws Exception{
        System.out.println("not IMplemented");
        return new ArrayList<ImageDescriptor>();

    };
    public boolean deleteDescriptor(String id) throws Exception{
        Connection c = connect(); //abre a conexão com o bd
        try{

            String query = "delete from imageDescriptor where uuid='"+id+"';";
            System.out.println(query);
            PreparedStatement s = c.prepareStatement(query);
            boolean res = s.execute();

            s.close();
            
            return true;
        } catch (SQLException ex) {
            
            throw new Exception("sql exception: " + ex.getMessage());

            
        }
        finally{
            //fecha a conexão com o bd
            c.close();
        }
    };
}
