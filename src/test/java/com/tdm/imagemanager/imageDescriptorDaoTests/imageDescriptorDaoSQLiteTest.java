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

import com.tdm.imagemanager.DAO.implementations.sqlite.imageDescriptorDaoSQLite;
import com.tdm.imagemanager.DAO.implementations.sqlite.categoriesDaoSQLite;
import com.tdm.imagemanager.DAO.implementations.sqlite.galleriesDaoSQLite;
import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.categoriesDaoInterface;
import com.tdm.imagemanager.classes.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class imageDescriptorDaoSQLiteTest {
    private final imageDescriptorDaoInterface imageDaoImpl= new imageDescriptorDaoSQLite();
    private static  ImageDescriptor descriptor1, descriptor2;
    private static Category category1, category2,category3,category4;
    private static Gallery gallery1, gallery2,gallery3, gallery4;
    private static ArrayList<String> categoriesIds = new ArrayList<String>();
    private static ArrayList<String> galleriesIds= new ArrayList<String>();

    @BeforeAll
    static void initDescriptor(){

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

        categoriesIds.add(category1.getName());
        categoriesIds.add(category2.getName());


        gallery1 = new Gallery("Fotos do natal", UUID.randomUUID().toString());
        gallery2 = new Gallery("Aniversário annia", UUID.randomUUID().toString());
        gallery3 = new Gallery("missão strix",UUID.randomUUID().toString());
        gallery4 = new Gallery("server update",UUID.randomUUID().toString());

        galleriesIds.add(gallery1.getId());
        galleriesIds.add(gallery2.getId());

        categoriesDaoInterface categoryDao = new categoriesDaoSQLite();
        

        galleriesDaoInterface galleryDao = new galleriesDaoSQLite();
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:imagemanagerdb.sqlite");
            String queries[]= {"image_gallery","image_category","category","gallery","img_descriptor"};
            for(String query : queries){
                PreparedStatement s = connection.prepareStatement("delete from "+query);
                boolean result = s.execute();
            }
            connection.close();
            categoryDao.addCategory(category1);
            categoryDao.addCategory(category2);
            categoryDao.addCategory(category3);
            categoryDao.addCategory(category4);
            galleryDao.addGallery(gallery1);
            galleryDao.addGallery(gallery2);
            galleryDao.addGallery(gallery3);
            galleryDao.addGallery(gallery4);
        } catch (Exception e) {
            System.err.println("Cannot insert categories to test environment"+ e.getMessage());
        }

        try {
            galleryDao.addGallery(gallery1);
            galleryDao.addGallery(gallery2);
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
            boolean result = imageDaoImpl.addDescriptorToCategory(descriptor1.getId(), category3.getName());
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category3.getName());
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
            ArrayList<String> descriptors = imageDaoImpl.findByCategory(category1.getName());
            assertEquals(1, descriptors.size());
            assertEquals(descriptors.get(0),descriptor2.getId());
            descriptors = imageDaoImpl.findByCategory(category2.getName());
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
            ArrayList<ImageDescriptor> result = imageDaoImpl.getAllDescriptors();
            assertEquals(2,result.size());
            assertEquals(ImageDescriptor.class, result.get(0).getClass());
            assertEquals(ImageDescriptor.class, result.get(1).getClass());
        });
    }
    
    @Test
    @Order(2)
    void getDescriptorsByCategoryTest(){

    }

    @Test
    @Order(2)
    void getDescriptorsByGalleryTest(){

    }

    @Test
    @Order(3)
    void removeDescriptorFromCategoryTest(){

    }

    @Test
    @Order(3)
    void removeDescriptorFromGalleryTest(){

    }
    
    @Test
    @Order(3)
    void removeDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.removeDescriptor(descriptor1.getId());
            boolean result2 = imageDaoImpl.removeDescriptor(descriptor2.getId());
            assertTrue(result2);
            assertTrue(result);
        });
    }
}
