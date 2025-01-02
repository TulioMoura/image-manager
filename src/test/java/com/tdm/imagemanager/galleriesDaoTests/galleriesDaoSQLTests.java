package com.tdm.imagemanager.galleriesDaoTests;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

import javax.accessibility.AccessibleAttributeSequence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.tdm.imagemanager.DAO.implementations.SQL.galleriesDaoSQL;
import com.tdm.imagemanager.DAO.implementations.SQL.initSQLDB;
import com.tdm.imagemanager.classes.baseApplication.Gallery;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;

import io.github.cdimascio.dotenv.Dotenv;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class galleriesDaoSQLTests {
    private final galleriesDaoSQL galleryDao = new galleriesDaoSQL();
    private static Gallery gallery1, gallery2;

     @BeforeAll
     static void initEnvironment(){
      Dotenv environment = Dotenv.load();
      String db_url =environment.get(System.getProperty("TEST").equals("TRUE") ? "TESTING_DATABASE_URL":"DATABASE_URL");
      System.out.println(db_url);
      System.out.println("here !!");
      initSQLDB.cleanup(db_url);
      
      
         UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        gallery1 = new Gallery("familia", id1.toString());
        gallery2 = new Gallery("carros", id2.toString());
     }


     @Test
     @Order(1)
     void addGalleryTest(){
        assertDoesNotThrow(()->{
            boolean insert1 = galleryDao.addGallery(gallery1);
            boolean insert2 = galleryDao.addGallery(gallery2);
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
            Gallery result1 = galleryDao.getOneGallery(gallery1.getId());
            assertEquals(gallery1.getId(), result1.getId());
            
        });
     }

     @Test
     @Order(2)
     void getAllGalleriesTest(){
        assertDoesNotThrow(()->{
         System.out.println("2");
            ArrayList<String> result = galleryDao.getAllGalleries();
            
            assertEquals(2,result.size());
            
        });
     }

     @Test 
     @Order(3)
     void removeGalleryTest(){
        assertDoesNotThrow(()->{
            boolean result = galleryDao.removeGallery(gallery1.getId());
            assertTrue(result);
            System.out.println("3");
            
        });
     }

}
