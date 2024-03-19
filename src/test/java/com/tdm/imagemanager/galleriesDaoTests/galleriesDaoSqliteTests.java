package com.tdm.imagemanager.galleriesDaoTests;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

import javax.accessibility.AccessibleAttributeSequence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.tdm.imagemanager.DAO.implementations.sqlite.galleriesDaoSQLite;
import com.tdm.imagemanager.classes.Gallery;
import com.tdm.imagemanager.classes.ImageDescriptor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class galleriesDaoSqliteTests {
    private final galleriesDaoSQLite galleryDao = new galleriesDaoSQLite();
    private static Gallery gallery1, gallery2;

     @BeforeAll
     static void initEnvironment(){
        try{
         Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
         PreparedStatement s = connection.prepareStatement("delete from gallery;delete from category;delete from img_descriptor;delete from image_category;delete from image_gallery;");
         s.execute();
         connection.close();
      }catch(Exception ex){
         System.out.println(ex);
      }
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
            ArrayList<Gallery> result = galleryDao.getAllGalleries();
            for(Gallery  g : result){
               System.out.println(g.getId()+"test");
            }
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
