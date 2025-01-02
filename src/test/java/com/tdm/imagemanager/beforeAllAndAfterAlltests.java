package com.tdm.imagemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.tdm.imagemanager.DAO.implementations.SQL.initSQLDB;

import io.github.cdimascio.dotenv.Dotenv;

public class beforeAllAndAfterAlltests {
    @BeforeAll
    public static void setup(){
        System.out.println("SETTING UP ENVIRONMENT");
        try {
            Dotenv environment = Dotenv.load();
            String db_url =environment.get(System.getProperty("TEST").equals("TRUE") ? "TESTING_DATABASE_URL":"DATABASE_URL");
            initSQLDB.setup(db_url);

            
            Connection connection = DriverManager.getConnection(db_url);
            String queries[]= {"image_gallery","image_category","category","gallery","img_descriptor"};
            for(String query : queries){
                PreparedStatement s = connection.prepareStatement("delete from "+query);
                boolean result = s.execute();
            }
            connection.close();

            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("THERE WAS A PROBLEM DURING TEST ENV SETUP, CHECK LOG FOR INFO");
        }
        

            

    }
}
