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

import com.tdm.imagemanager.DAO.implementations.sqlite.galleriesDaoSQLite;
import com.tdm.imagemanager.classes.Gallery;

public class galleriesDaoSqliteTests {
    private final galleriesDaoSQLite galleryDao = new galleriesDaoSQLite();
    private static Gallery gallery1, gallery2;

     @BeforeAll
     static void initEnvironment(){
        try{
         Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
         PreparedStatement s = connection.prepareStatement("delete from galleries;delete from categories;delete from imageDescriptor;delete from imageCategories;delete from imageGalleries;");
         s.execute();
         connection.close();
      }catch(Exception ex){
         System.out.println(ex);
      }
         UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        gallery1 = new Gallery("familia", id1.toString() );
        gallery2 = new Gallery("carros", id2.toString());
     }


     @Test
     @Order(1)
     void saveGalleryTest(){
        assertDoesNotThrow(()->{
            boolean insert1 = galleryDao.saveGallery(gallery1);
            boolean insert2 = galleryDao.saveGallery(gallery2);
            assertTrue(insert1);
            assertTrue(insert2);
        });
     }

     @Test
     @Order(2)
     void getOneGalleryTest(){
        assertDoesNotThrow(()->{
            Gallery result1 = galleryDao.getOneGallery(gallery1.getId());
            Gallery result2 = galleryDao.getOneGallery(gallery2.getId());
            assertEquals(gallery1.getId(),result1.getId());
            assertEquals(gallery2.getId(), result2.getId());
        });
     }

     @Test
     @Order(2)
     void getAllGalleriesTest(){
        assertDoesNotThrow(()->{
            ArrayList<Gallery> result = galleryDao.getAllGalleries();
            for(Gallery  g : result){
               System.out.println(g.getId()+"test");
            }
            assertEquals(2,result.size());
        });
     }

     @Test 
     @Order(3)
     void deleteGalleryTest(){
        assertDoesNotThrow(()->{
            boolean result = galleryDao.deleteGallery(gallery1.getId());
            assertTrue(result);
            
        });
     }

}
