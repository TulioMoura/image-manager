package com.tdm.imagemanager.imageDescriptorDaoTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.jupiter.api.*;

import com.tdm.imagemanager.DAO.implementations.sqlite.imageDescriptorDaoSQLite;
import com.tdm.imagemanager.classes.ImageDescriptor;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class imageDescriptorDaoSQLiteTest {
    private final imageDescriptorDaoSQLite imageDaoImpl= new imageDescriptorDaoSQLite();
    private static  ImageDescriptor imgDescriptor, newDescriptor;

    @BeforeAll
    static void initDescriptor(){

        ArrayList<String> caracteristics =  new ArrayList<String>();
        caracteristics.add("carro0");
        caracteristics.add("casa");
        UUID uuid = UUID.randomUUID(); 
        imgDescriptor = new ImageDescriptor(uuid.toString(),caracteristics);

        //novo item para adicionar no db
        UUID uuid2 = UUID.randomUUID(); 
        newDescriptor = new ImageDescriptor(uuid2.toString(),caracteristics);

    }

    @Test
    @Order(1)
    void saveDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result= imageDaoImpl.saveDescriptor(imgDescriptor);
            boolean result2= imageDaoImpl.saveDescriptor(newDescriptor);
            assertTrue(result);
            assertTrue(result2);
        });
    }

    @Test
    @Order(2)
    void getDescriptorTest(){
        assertDoesNotThrow(()->{
            ImageDescriptor  result = imageDaoImpl.getOneDescriptor(imgDescriptor.getId());
            System.out.println(imgDescriptor.getId()+" "+imgDescriptor.getDate()+" "+imgDescriptor.getCharacteristics());
            System.out.println(result.getId()+" "+result.getDate()+" "+result.getCharacteristics());
            assertEquals(imgDescriptor.getId(), result.getId());
            assertEquals(imgDescriptor.getDate(), result.getDate());
            assertEquals(imgDescriptor.getCharacteristics(), result.getCharacteristics());
        });
    }

    @Test
    @Order(2)
    void getAllDescriptorsTest(){
        assertDoesNotThrow(()->{
            

            //expected arraylist to be returned by database 
            ArrayList<ImageDescriptor> expectedList = new ArrayList<ImageDescriptor>();
            expectedList.add(imgDescriptor);
            expectedList.add(newDescriptor);

            //test
            ArrayList<ImageDescriptor> result = imageDaoImpl.getAllDescriptors();
            assertEquals(expectedList, result);
        });
    }

    @Test
    @Order(3)
    void deleteDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.deleteDescriptor(imgDescriptor.getId());
            boolean result2 = imageDaoImpl.deleteDescriptor(newDescriptor.getId());
            assertTrue(result2);
            assertTrue(result);
        });
    }
}
