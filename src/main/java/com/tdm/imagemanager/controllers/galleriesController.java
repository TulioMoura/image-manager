package com.tdm.imagemanager.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.tdm.imagemanager.DAO.implementations.SQL.galleriesDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.Gallery;

@CrossOrigin
@RestController
public class galleriesController {
    static final galleriesDaoInterface galleryDao = new galleriesDaoSQL();
    @GetMapping("/galleries")
    public ArrayList<String> getGalleries(){
        try{
            return galleryDao.getAllGalleries();
        }
        catch(Exception exe){
            System.out.println(exe);
            return null;
        }
        
    } 
    @GetMapping("/galleries/{id}")
    public Gallery getOneGallery(@PathVariable("id") String id){
        try {
            return galleryDao.getOneGallery(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @PostMapping("/galleries")
    public Gallery addGallery(@RequestBody Gallery gallery){
        try {
            galleryDao.addGallery(gallery);
            return galleryDao.getOneGallery(gallery.getId());

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @DeleteMapping("/galleries")
    public void removeGallery(@RequestParam("id") String id){
        try {
            galleryDao.removeGallery(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
