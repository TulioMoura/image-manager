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

import com.tdm.imagemanager.DAO.implementations.SQL.categoriesDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.categoriesDaoInterface;
import com.tdm.imagemanager.DAO.implementations.SQL.galleriesDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.Category;
import com.tdm.imagemanager.classes.baseApplication.Gallery;

@CrossOrigin
@RestController
public class categoriesController {
    static final categoriesDaoInterface categoryDao = new categoriesDaoSQL();

    @GetMapping("/categories")
    public ArrayList<String> getCategories() {
        try {
            return categoryDao.getAllCategories();
        } catch (Exception exe) {
            System.out.println(exe);
            return null;
        }

    }

    @GetMapping("/categories/{id}")
    public Category getOneCategory(@PathVariable("id") String id) {
        try {
            return categoryDao
                    .getOneCategory(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category gallery) {
        try {
            categoryDao
                    .addCategory(gallery);
            return categoryDao
                    .getOneCategory(gallery.getName());

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @DeleteMapping("/categories")
    public void removeCategory(@RequestParam("id") String id) {
        try {
            categoryDao.removeCategory(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
