package com.tdm.imagemanager.classes;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class galleriesTest {
    private final Gallery gallery = new Gallery("teste", "uuidteste");

    @Test
    void constructorTest(){
    assertEquals("teste",gallery.getName());
    assertEquals("uuidteste", gallery.getId());
    }

    @Test
    void getIdTest(){
        assertEquals("uuidteste", gallery.getId());
    }
    @Test
    void creationDataTest(){
        String dateOnObjetct = gallery.getDate().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");
        String currentDate = new Date().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");

        System.out.println(dateOnObjetct);
        assertEquals(currentDate, dateOnObjetct);
    }

    @Test
    void getNameTest(){
        assertEquals("teste",gallery.getName() );
    }

    @Test 
    void setNameTest(){
        gallery.setName("tulio");
        assertEquals("tulio", gallery.getName());
    }

    @Test
    void getImagesTest()throws Exception{
        gallery.addImage("id1");
        assertTrue(gallery.getImages().contains("id1"));        
    }

    @Test 
    void addImagesTest() throws Exception{
        gallery.addImage("id2");
        gallery.addImage("id23");

        ArrayList<String> result = gallery.getImages();

        assertTrue(result.contains("id2"));
        assertTrue(result.contains("id23"));
    }

    @Test
    void removeImageTest()throws Exception{
        gallery.addImage("id2remove");
        gallery.removeImage("id2remove");
        ArrayList<String> result = gallery.getImages();

        assertFalse(result.contains("id2remove"));
    }
}
