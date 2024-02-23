package com.tdm.imagemanager.imageDaoToLocalFolderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.tdm.imagemanager.DAO.implementations.imageDaoToLocalFolder;

public class imageDaoToLocalFolderTest {
    private final imageDaoToLocalFolder imageDAO = new imageDaoToLocalFolder();

    @Test
    void saveImageTest() {
        imageDAO.saveImage("teste.png","testeteste123");
        File image = imageDAO.getImage("testeteste123");
        File srcimage = new File("teste.png");
        assertNotNull(image);
    }

    @Test 
    void getImageTest(){
        File srcimage = new File("teste.png");
        File savedImage = imageDAO.getImage("testeteste123");
        assertEquals(srcimage.length(),savedImage.length());
    }

    @Test 
    void deleteImageTest(){
        try{
        String result = imageDAO.deleteImage("testeteste123");
        assertEquals("Image deleted!",result);
        System.out.println(result);
        }
        catch (IOException exe){
            System.out.println(exe);
        }
        
    }

    @Test
    void cannotDeleteInexistentImage(){
        Exception exe = assertThrows(IOException.class,()->{imageDAO.deleteImage("null");});
        assertEquals("Cannot delete image.",exe.getMessage());
    }
}
