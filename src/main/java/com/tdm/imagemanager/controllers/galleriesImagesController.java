package com.tdm.imagemanager.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.tdm.imagemanager.DAO.implementations.SQL.galleriesDaoSQL;
import com.tdm.imagemanager.DAO.implementations.SQL.imageDescriptorDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.galleriesDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;
import com.tdm.imagemanager.classes.transferObjects.ImageGalleries.descriptorGallery;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;
@CrossOrigin
@RestController
public class galleriesImagesController {
    private static final imageDescriptorDaoInterface imageDao = new imageDescriptorDaoSQL();
    private static final galleriesDaoInterface galleryDao = new galleriesDaoSQL();

    @PostMapping("/galleries/images")
    public void addImageToGallery(@RequestBody descriptorGallery requestObject, HttpServletResponse response){
        try {
             imageDao.addDescriptorToGallery(requestObject.getImageId(), requestObject.getGalleryId());
             return ;
        } catch (Exception e) {
            // TODO: handle exception
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
       

    }
}
