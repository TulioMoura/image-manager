package com.tdm.imagemanager.controllers;


import java.io.IOException;
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
import com.tdm.imagemanager.DAO.implementations.SQL.imageDescriptorDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.imageDaoInterface;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.ImageDescriptor;
import com.tdm.imagemanager.classes.descriptorFile;
import com.tdm.imagemanager.utils.*;

import io.github.cdimascio.dotenv.Dotenv;

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
		String tmpFolder = Dotenv.load().get("TEMPORARY_FOLDER"); 
		String filename = file.getOriginalFilename();
		String fileExtension = Utils.getFileExtension(filename);

		if ( ! imageDao.checkIfImageExists(id)){
		Path temporaryPath = Paths.get(tmpFolder+id+"."+fileExtension); 
		try{
			Path result = Files.write(temporaryPath,file.getBytes(), new OpenOption[0]) ;
		imageDao.saveImage(result.toString(), id);
		return;
		}
		catch (IOException ex){
			System.out.println(ex);
			return ;
		}
		}
		else{
			System.out.println("upload ja efetuado");
			return; 
		}
	}  
}
