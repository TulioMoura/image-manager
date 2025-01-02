package com.tdm.imagemanager._classes;


import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.tdm.imagemanager.classes.baseApplication.Category;

class categoriesTest{
    private final Category category = new Category("teste");

    @Test
    void constructorTest(){
    assertEquals("teste",category.getName());
    }

    @Test
    void creationDataTest(){
        String dateOnObjetct = category.getDate().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");
        String currentDate = new Date().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");

        assertEquals(currentDate, dateOnObjetct);
    }

    @Test
    void getNameTest(){
        assertEquals("teste",category.getName() );
    }

    @Test 
    void setNameTest(){
        category.setName("tulio");
        assertEquals("tulio", category.getName());
    }

    @Test
    void getImagesTest()throws Exception{
        category.addImage("id1");
        assertTrue(category.getImages().contains("id1"));        
    }

    @Test 
    void addImagesTest() throws Exception{
        category.addImage("id2");
        category.addImage("id23");

        ArrayList<String> result = category.getImages();

        assertTrue(result.contains("id2"));
        assertTrue(result.contains("id23"));
    }

    @Test
    void removeImageTest()throws Exception{
        category.addImage("id2remove");
        category.removeImage("id2remove");
        ArrayList<String> result = category.getImages();

        assertFalse(result.contains("id2remove"));
    }
}