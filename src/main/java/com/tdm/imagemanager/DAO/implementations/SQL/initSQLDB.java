package com.tdm.imagemanager.DAO.implementations.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.nio.file.*;
import io.github.cdimascio.dotenv.Dotenv;

public class initSQLDB {
    public static void main(String[] args){

        Dotenv environment = Dotenv.load();
        String database_url = environment.get("DATABASE_URL");
        try{
        Connection connection = DriverManager.getConnection(database_url);
        
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
