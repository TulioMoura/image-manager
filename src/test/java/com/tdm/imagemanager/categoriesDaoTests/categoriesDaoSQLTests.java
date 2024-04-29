package com.tdm.imagemanager.categoriesDaoTests;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.tdm.imagemanager.DAO.implementations.SQL.categoriesDaoSQL;
import com.tdm.imagemanager.DAO.implementations.SQL.initSQLDB;
import com.tdm.imagemanager.classes.baseApplication.Category;
import com.tdm.imagemanager.classes.baseApplication.Gallery;

import io.github.cdimascio.dotenv.Dotenv;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class categoriesDaoSQLTests {
    private final categoriesDaoSQL categoryDAO = new categoriesDaoSQL();
    private static Category category1, category2;

     @BeforeAll
     static void initEnvironment(){
      Dotenv environment = Dotenv.load();
      String db_url = environment.get((System.getProperty("TEST")=="TRUE")? "TESTING_DATABASE_URL":"DATABASE_URL");
      System.out.println(db_url);
      System.out.println("here !!");
      initSQLDB.cleanup(db_url);
      
      
         UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        category1 = new Category(id1.toString());
        category2 = new Category(id2.toString());
     }


     @Test
     @Order(1)
     void addGalleryTest(){
        assertDoesNotThrow(()->{
            boolean insert1 = categoryDAO.addCategory(category1);
            boolean insert2 = categoryDAO.addCategory(category2);
            assertTrue(insert1);
            assertTrue(insert2);
            System.out.println("1");
        });
     }

     @Test
     @Order(2)
     void getOneGalleryTest(){
        assertDoesNotThrow(()->{
         System.out.println("2");
            Category result1 = categoryDAO.getOneCategory(category1.getName());
            assertEquals(category1.getName(), result1.getName());
            
        });
     }

     @Test
     @Order(2)
     void getAllGalleriesTest(){
        assertDoesNotThrow(()->{
         System.out.println("2");
            ArrayList<String> result = categoryDAO.getAllCategories();
            assertEquals(2,result.size());
            
        });
     }

     @Test 
     @Order(3)
     void removeGalleryTest(){
        assertDoesNotThrow(()->{
            boolean result = categoryDAO.removeCategory(category1.getName());
            assertTrue(result);
            System.out.println("3");
            
        });
     }

}

