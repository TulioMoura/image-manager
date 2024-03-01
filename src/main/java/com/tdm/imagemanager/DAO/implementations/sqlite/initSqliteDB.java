package com.tdm.imagemanager.DAO.implementations.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.nio.file.*;
public class initSqliteDB {
    public static void main(String[] args){
        try{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
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
