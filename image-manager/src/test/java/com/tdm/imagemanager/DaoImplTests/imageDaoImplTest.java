package com.tdm.imagemanager.DaoImplTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.tdm.imagemanager.DAO.implementations.imageDaoImpl;

public class imageDaoImplTest {
    private final imageDaoImpl imageDAO = new imageDaoImpl();

    @Test
    void saveImageTest(){
        File teste = new File("teste.png");
                imageDAO.saveImage(teste,"testeteste");
                teste.renameTo(new File("testeteste"));
                assertEquals(teste, imageDAO.getImage("testeteste"));
    }

    @Test 
    void getImageTest(){
        
        File teste = new File();
        teste.renameTo(new File("testeteste"));
        assertEquals(teste, imageDAO.getImage("testeteste"));
    }
}
