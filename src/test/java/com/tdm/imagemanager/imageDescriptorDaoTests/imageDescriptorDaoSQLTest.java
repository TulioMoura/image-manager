package com.tdm.imagemanager.imageDescriptorDaoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

import org.junit.jupiter.api.*;

import com.tdm.imagemanager.DAO.implementations.SQL.imageDescriptorDaoSQL;
import com.tdm.imagemanager.DAO.implementations.SQL.initSQLDB;
import com.tdm.imagemanager.DAO.implementations.SQL.categoriesDaoSQL;
import com.tdm.imagemanager.DAO.implementations.SQL.galleriesDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager._classes.*;
import com.tdm.imagemanager.DAO.interfaces.categoriesDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.Category;
import com.tdm.imagemanager.classes.baseApplication.Gallery;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;

import io.github.cdimascio.dotenv.Dotenv;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class imageDescriptorDaoSQLTest {
    private final imageDescriptorDaoInterface imageDaoImpl= new imageDescriptorDaoSQL();
    private static  ImageDescriptor descriptor1, descriptor2;
    private static Category category1, category2,category3,category4;
    private static Gallery gallery1, gallery2,gallery3, gallery4;
    private static ArrayList<String> categoriesIds = new ArrayList<String>();
    private static ArrayList<String> galleriesIds= new ArrayList<String>();

    @BeforeAll
    static void initDescriptor(){
        Dotenv environment = Dotenv.load();
        String db_url =environment.get(environment.get("TEST")=="TRUE"? "TESTING_DATABASE_URL":"DATABASE_URL");
        initSQLDB.cleanup(db_url);

        ArrayList<String> caracteristics =  new ArrayList<String>();
        caracteristics.add("carro0");
        caracteristics.add("casa");
        UUID uuid = UUID.randomUUID(); 
        descriptor1 = new ImageDescriptor(uuid.toString(),caracteristics);

        //novo item para adicionar no db
        UUID uuid2 = UUID.randomUUID(); 
        descriptor2 = new ImageDescriptor(uuid2.toString(),caracteristics);

        category1 = new Category("carros");
        category2 = new Category("paisagens");
        category3 = new Category("bicicletas");
        category4 = new Category("praças");

        categoriesIds.add(category2.getName());
        categoriesIds.add(category3.getName());


        gallery1 = new Gallery("Fotos do natal", UUID.randomUUID().toString());
        gallery2 = new Gallery("Aniversário annia", UUID.randomUUID().toString());
        gallery3 = new Gallery("missão strix",UUID.randomUUID().toString());
        gallery4 = new Gallery("server update",UUID.randomUUID().toString());

        galleriesIds.add(gallery2.getId());
        galleriesIds.add(gallery3.getId());

        categoriesDaoInterface categoryDao = new categoriesDaoSQL();
        

        galleriesDaoInterface galleryDao = new galleriesDaoSQL();
        
        try {
            
            categoryDao.addCategory(category1);
            categoryDao.addCategory(category2);
            categoryDao.addCategory(category3);
            categoryDao.addCategory(category4);
            
        } catch (Exception e) {
            System.err.println("Cannot insert categories to test environment"+ e.getMessage());
        }

        try {
            galleryDao.addGallery(gallery1);
            galleryDao.addGallery(gallery2);
            galleryDao.addGallery(gallery3);
            galleryDao.addGallery(gallery4);
            } catch (Exception e) {
                System.err.println("Cannot insert galleries to test environment"+ e.getMessage());
            }
        
    }

    @Test
    @Order(1)
    void saveDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result= imageDaoImpl.saveDescriptor(descriptor1,new ArrayList<String>(),new ArrayList<String>());
            boolean result2= imageDaoImpl.saveDescriptor(descriptor2,new ArrayList<String>(), new ArrayList<String>());
            assertTrue(result);
            assertTrue(result2);
        });
    }

    @Test
    @Order(2)
    void addDescriptorToCategoryTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.addDescriptorToCategory(descriptor1.getId(), category1.getName());
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category1.getName());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor1.getId());
        });
        
    }
    @Test
    @Order(2)
    void addDescriptorToCategoriesTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.addDescriptorToCategories(descriptor2.getId(), categoriesIds);
            assertTrue(result);
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category2.getName());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor2.getId());
            descriptors = imageDaoImpl.findByCategory(category3.getName());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor2.getId());
        });
    }
    @Test
    @Order(2)
    void addDescriptorsToCategoryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = new ArrayList<String>();
            descriptors.add(descriptor1.getId());
            descriptors.add(descriptor2.getId());
            boolean result = imageDaoImpl.addDescriptorsToCategory(descriptors, category4.getName());
            assertTrue(result);
            ArrayList<String> addedDescriptors = imageDaoImpl.findByCategory(category4.getName());
            assertEquals(2, addedDescriptors.size());
        });
    }


    @Test
    @Order(2)
    void addDescriptorToGalleriesTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.addDescriptorToGalleries(descriptor2.getId(), galleriesIds);
            assertTrue(result);
            ArrayList<String> descriptors = imageDaoImpl.findByGallery(gallery2.getId());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor2.getId());
            descriptors = imageDaoImpl.findByGallery(gallery3.getId());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor2.getId());
        });
    }

    @Test
    @Order(2)
    void addDescriptorsToGalleryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = new ArrayList<String>();
            descriptors.add(descriptor1.getId());
            descriptors.add(descriptor2.getId());
            boolean result = imageDaoImpl.addDescriptorsToGallery(descriptors, gallery4.getId());
            assertTrue(result);
            ArrayList<String> addedDescriptors = imageDaoImpl.findByGallery(gallery4.getId());
            assertEquals(2, addedDescriptors.size());
        });
    }

    @Test 
    @Order(2)
    void addDescriptorToGalleryTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.addDescriptorToGallery(descriptor1.getId(), gallery1.getId());
            ArrayList<String> descriptors = imageDaoImpl.findByGallery(gallery1.getId());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor1.getId());
        });
    }

    @Test
    @Order(2)
    void getDescriptorTest(){
    
        assertDoesNotThrow(()->{
            ImageDescriptor  result = imageDaoImpl.getDescriptor(descriptor1.getId());
            System.out.println(descriptor1.getId()+" "+descriptor1.getDate()+" "+descriptor1.getCharacteristics());
            System.out.println(result.getId()+" "+result.getDate()+" "+result.getCharacteristics());
            assertEquals(descriptor1.getId(), result.getId());
            assertEquals(descriptor1.getDate(), result.getDate());
            assertEquals(descriptor1.getCharacteristics(), result.getCharacteristics());
        });
    }

    @Test
    @Order(2)
     void getAllDescriptorsTest(){
        assertDoesNotThrow(()->{
            
            //test
            ArrayList<String> result = imageDaoImpl.getAllDescriptors();
            assertEquals(2,result.size());
            assertEquals(String.class, result.get(0).getClass());
            assertEquals(String.class, result.get(1).getClass());
        });
    }
    
    @Test
    @Order(3)
    void findByCategoryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category4.getName());
            assertEquals(2, descriptors.size());
            assertTrue( descriptor1.getId().equals(descriptors.get(0)) ^descriptor1.getId().equals(descriptors.get(1)));
            assertTrue(descriptor2.getId().equals(descriptors.get(0)) ^ descriptor2.getId().equals(descriptors.get(1)));
        }); 
    }

    @Test
    @Order(3)
    void findByGalleryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = imageDaoImpl.findByGallery(gallery4.getId());
            assertEquals(2, descriptors.size());
            assertTrue( descriptor1.getId().equals(descriptors.get(0)) ^descriptor1.getId().equals(descriptors.get(1)));
            assertTrue(descriptor2.getId().equals(descriptors.get(0)) ^ descriptor2.getId().equals(descriptors.get(1)));
        });
    }

    @Test
    @Order(5)
    void removeDescriptorFromCategoryTest(){
        assertDoesNotThrow(()->{
            imageDaoImpl.removeDescriptorFromCategory(descriptor1.getId(), category1.getName());
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category1.getName());
            assertEquals(0, descriptors.size());
        });
    }

    @Test
    @Order(6)
    void removeDescriptorFromCategoriesTest(){
        assertDoesNotThrow(()->{
            imageDaoImpl.removeDescriptorFromCategories(descriptor2.getId(), categoriesIds);
            categoriesDaoInterface categoryDaoImpl = new categoriesDaoSQL();
            ArrayList<String> categories = categoryDaoImpl.getCategoriesByDescriptorId(descriptor2.getId());
            assertEquals(1, categories.size());
        });
    }

    @Test
    @Order(7)
    void removeDescriptorsFromCategoryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = new ArrayList<String>();
            descriptors.add(descriptor1.getId());
            descriptors.add(descriptor2.getId());
            imageDaoImpl.removeDescriptorsFromCategory(descriptors, category4.getName());
            ArrayList<String> foundDescriptors = imageDaoImpl.findByCategory(category4.getName());
            assertEquals(0, foundDescriptors.size());
        });
    }
    @Test
    @Order(5)
    void removeDescriptorFromGalleryTest(){
        assertDoesNotThrow(()->{
            imageDaoImpl.removeDescriptorFromGallery(descriptor1.getId(), gallery1.getId());
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(gallery1.getId());
            assertEquals(0, descriptors.size());
          });
        
    }
    
    @Test
    @Order(6)
    void removeDescriptorFromGalleriesTest(){
        assertDoesNotThrow(()->{
            imageDaoImpl.removeDescriptorFromGalleries(descriptor2.getId(), galleriesIds);
            galleriesDaoInterface galleryDaoImpl = new galleriesDaoSQL();
            ArrayList<String> galleries = galleryDaoImpl.getGalleriesByDescriptorId(descriptor2.getId());
            assertEquals(1, galleries.size());
          });
        
    }
    @Test
    @Order(7)
    void removeDescriptorsFromGalleryTest(){
        assertDoesNotThrow(()->{
            ArrayList<String> descriptors = new ArrayList<String>();
            descriptors.add(descriptor1.getId());
            descriptors.add(descriptor2.getId());
            imageDaoImpl.removeDescriptorsFromGallery(descriptors, gallery4.getId());
            ArrayList<String> foundDescriptors = imageDaoImpl.findByGallery(gallery4.getId());
            assertEquals(0, foundDescriptors.size());
          });
        
    }
    
    @Test
    @Order(8)
    void removeDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.removeDescriptor(descriptor1.getId());
            boolean result2 = imageDaoImpl.removeDescriptor(descriptor2.getId());
            assertTrue(result2);
            assertTrue(result);
        });
        assertThrows(java.lang.Exception.class,()->{
            imageDaoImpl.getDescriptor(descriptor1.getId());
        });
    }
}
