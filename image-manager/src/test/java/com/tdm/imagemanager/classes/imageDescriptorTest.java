package com.tdm.imagemanager.classes;
import com.tdm.imagemanager.classes.*;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import java.beans.Expression;
import java.beans.Transient;
import java.lang.reflect.Array;
import java.awt.Image;
import java.awt.Image.*;
import java.awt.image.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

class ImageDescriptorTest{
    private final ImageDescriptor imageDescriptorInstance = new ImageDescriptor(null, null, null);

    @Test
    void getIdTest(){
        assertEquals("testeteste", imageDescriptorInstance.getId());
    }

    @Test
    void addCharacteristicTest(){
        imageDescriptorInstance.addCharacteristic("carro");
        imageDescriptorInstance.addCharacteristic("volkswagen");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("carro");
        expected.add("volkswagen");

        assertEquals(expected, imageDescriptorInstance.getCharacteristics());
    }

    @Test
    void getCharacteristicsTest(){
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("teste1");
        expected.add("teste2");

        imageDescriptorInstance.addCharacteristic("teste1");
        imageDescriptorInstance.addCharacteristic("teste2");

        assertEquals(expected, imageDescriptorInstance.getCharacteristics());
    }
    
    @Test
    void removeCharacteristicTest(){
        imageDescriptorInstance.addCharacteristic("removeTest");
        imageDescriptorInstance.getCharacteristics().remove("removeTest");
        assertFalse(imageDescriptorInstance.getCharacteristics().contains("removeTest"));
    }

    @Test
    void removeAllCharacteristicsTest(){
        ArrayList<String> expected = new ArrayList<>();
        imageDescriptorInstance.addCharacteristic("teste1");
        imageDescriptorInstance.addCharacteristic("teste3");
        imageDescriptorInstance.removeAllCharacteristics();
        assertEquals(expected, imageDescriptorInstance.getCharacteristics());
    }
    @Test
    void getDateTest(){
        String dateOnObjetct = imageDescriptorInstance.getDate().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");
        String currentDate = new Date().toString().replaceAll("\\d\\d:\\d\\d:\\d\\d","00:00:00");
        System.out.println(dateOnObjetct);
        assertEquals(currentDate, dateOnObjetct);
    }

    @Test
    void getThumbnailTest(){
        assertEquals(Image.class,imageDescriptorInstance.getThumbnail().getClass());
    }


    @Test
        void constructorTest(){
            assertEquals("testeteste",imageDescriptorInstance.getId());
            
        }
}