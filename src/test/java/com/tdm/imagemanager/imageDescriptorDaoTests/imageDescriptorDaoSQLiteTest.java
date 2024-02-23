package com.tdm.imagemanager.DaoImplTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.jupiter.api.*;

import com.tdm.imagemanager.DAO.implementations.imageDescriptorDaoSQLite;
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
            System.out.println(1);
            System.out.println("lklkkkjlklk");
            assertTrue(result);
        });
    }

    @Test
    @Order(2)
    void getDescriptorTest(){
        assertDoesNotThrow(()->{
            ImageDescriptor  result = imageDaoImpl.getOneDescriptor(imgDescriptor.getId());
            System.out.println(2);
            assertEquals(imgDescriptor, result);
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
            System.out.println(3);
            assertEquals(expectedList, result);
        });
    }

    @Test
    @Order(3)
    void deleteDescriptorTest(){
        assertDoesNotThrow(()->{
            boolean result = imageDaoImpl.deleteDescriptor(imgDescriptor.getId());
            boolean result2 = imageDaoImpl.deleteDescriptor(newDescriptor.getId());
            System.out.println(4);
            assertTrue(result2);
            assertTrue(result);
        });
    }
}
