package com.tdm.imagemanager.controllers;


import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tdm.imagemanager.DAO.implementations.localFolder.imageDaoToLocalFolder;
import com.tdm.imagemanager.DAO.implementations.sqlite.imageDescriptorDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.ImageDescriptor;
import com.tdm.imagemanager.classes.descriptorFile;

@RestController
public class images_controller {
	private static  imageDescriptorDaoInterface imageDescriptorDao = new imageDescriptorDaoSQL();
	private static imageDaoToLocalFolder imageDao = new imageDaoToLocalFolder();
	
    @GetMapping("/images")
	public ArrayList<ImageDescriptor> index() {
		try{
			ArrayList<ImageDescriptor> images = imageDescriptorDao.getAllDescriptors();

			return images;
		}
		catch(Exception ex){
			return null;
		}
		
	}

	@PostMapping("/images")
	public ImageDescriptor insertImage(@RequestBody ImageDescriptor descriptor){
		try{
		imageDescriptorDao.saveDescriptor(descriptor, null, null);
		return imageDescriptorDao.getDescriptor(descriptor.getId());
		}
		catch(Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}

	@PostMapping("/images/upload")
	public void uploadImage( @RequestParam("id") String id , @RequestParam ("file") MultipartFile file){
		System.out.println(id);	
		System.out.println(file.toString());
		
	
		try{
			imageDao.getImage(id);
		}
		catch(Exception ex){
			try{
				imageDescriptorDao.getDescriptor(id);
				Path temporaryPath = Paths.get("tmp/"); 
				Path result = Files.write(temporaryPath,file.getBytes(), new OpenOption[0]) ;
				imageDao.saveImage(result.toString(), id);
				return;
			}
			catch(Exception execpt){
				
			}
			return;
		}
		return;
		 
	}  
}
