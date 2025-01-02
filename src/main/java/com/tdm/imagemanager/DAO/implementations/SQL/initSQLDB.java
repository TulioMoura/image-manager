package com.tdm.imagemanager.DAO.implementations.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.nio.file.*;
import io.github.cdimascio.dotenv.Dotenv;

public class initSQLDB {
    public static void main(String[] args){
        Dotenv environment = Dotenv.load();
        String database_url = environment.get("DATABASE_URL");
        setup(database_url);

    }

    public static void cleanup(String db_url){
            System.out.println("SETTING UP ENVIRONMENT");
            setup(db_url);
            try {        
                Connection connection = DriverManager.getConnection(db_url);
                String queries[]= {"image_gallery","image_category","category","gallery","img_descriptor"};
                for(String query : queries){
                    PreparedStatement s = connection.prepareStatement("delete from "+query);
                    boolean result = s.execute();
                }
                System.out.println("DB CLEANED");
                connection.close();
    
                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("THERE WAS A PROBLEM DURING TEST ENV SETUP, CHECK LOG FOR INFO");
            }
    }
    public static void setup(String db_url){
        
        try{
        Connection connection = DriverManager.getConnection(db_url);
        
        Path sqlScriptpath = Paths.get("scripts/sqlscript.sql");
        String  script = new String(Files.readAllBytes(sqlScriptpath));
        ArrayList<String> queries = new ArrayList<String>();
        String tmp = "";
        int index = 0;
        
        while(index<script.length()){
            char bt = script.charAt(index);
            if( bt == ';'){
                    tmp = tmp+';';
                    queries.add(tmp);
                    tmp = "";
                }
                else{
                    tmp = tmp+ bt ;
                }
                index++;
        }


        System.out.println(queries.toString());
        Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
        for (String string : queries) {
            stmt.addBatch(string);
        }
        stmt.executeBatch();

        System.out.println("Database created!");
        }
        catch(Exception ex){
            System.out.println("Error creating database!" + ex.getMessage());
            System.err.println(ex.toString());
        }
        
    }
}
