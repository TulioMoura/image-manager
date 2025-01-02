package com.tdm.imagemanager.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tdm.imagemanager.DAO.implementations.localFolder.imageDaoToLocalFolder;
import com.tdm.imagemanager.DAO.implementations.SQL.imageDescriptorDaoSQL;
import com.tdm.imagemanager.DAO.interfaces.imageDescriptorDaoInterface;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;
import com.tdm.imagemanager.utils.*;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class imagesController {
	private static  imageDescriptorDaoInterface imageDescriptorDao = new imageDescriptorDaoSQL();
	private static imageDaoToLocalFolder imageDao = new imageDaoToLocalFolder();
	
    @GetMapping("/images")
	public ArrayList<String> index() {
		try{
			ArrayList<String> images = imageDescriptorDao.getAllDescriptors();

			return images;
		}
		catch(Exception ex){
			return null;
		}
		
	}

	@GetMapping(
		value = "/images/{id}",
		produces = MediaType.IMAGE_JPEG_VALUE)
	public  byte[] index(@PathVariable("id") String id) {
		try{
			File image = imageDao.getImage(id);
			InputStream file = new FileInputStream(image);
			//byte[] buffer = new byte[];
			;
			return IOUtils.toByteArray(file);
			
		}
		catch(Exception ex){
			return null;
		}
		
	}

	@PostMapping("/images")
	public ImageDescriptor insertImage(@RequestBody ArrayList<String> characteristics){
		try{
		ImageDescriptor descriptor = new ImageDescriptor(characteristics);
		imageDescriptorDao.saveDescriptor(descriptor, new ArrayList<String>(), new ArrayList<String>());
		ImageDescriptor imageUploaded =  imageDescriptorDao.getDescriptor(descriptor.getId());
		return imageUploaded;
		}
		catch(Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}
  

	@DeleteMapping("/images")
	public void deleteImage(@RequestParam("id") String id){
		try{
			imageDescriptorDao.removeDescriptor(id);
			imageDao.deleteImage(id);
			
		}
		catch(Exception exe)
		{
			System.out.println(exe);
		}
	}

	@PostMapping("/images/upload")
	public void uploadImage( @RequestParam("id") String id , @RequestParam ("file") MultipartFile file, HttpServletResponse response){
		String tmpFolder = Dotenv.load().get("TEMPORARY_FOLDER"); 
		String filename = file.getOriginalFilename();
		String fileExtension = Utils.getFileExtension(filename);

		if ( !imageDao.checkIfImageExists(id)){
		Path temporaryPath = Paths.get(tmpFolder+id+"."+fileExtension); 
		try{
			Path result = Files.write(temporaryPath,file.getBytes(), new OpenOption[0]) ;
			imageDao.saveImage(result.toString(), id);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		catch (IOException ex){
			System.out.println(ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ;
		}
		}
		else{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return; 
		}
	}



	@PostMapping("/images/categories")
	public void addCategoriesToImage( @RequestParam("id") String id , @RequestBody ArrayList<String> categories, HttpServletResponse response){
		
		try{
		 	imageDescriptorDao.addDescriptorToCategories(id,categories);
			return;
		}
		catch (Exception ex){
			System.out.println(ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ;
		}
		
	}
	@DeleteMapping("/images/categories")
	public void removeCategoriesFromImage( @RequestParam("id") String id , @RequestBody ArrayList<String> categories, HttpServletResponse response){
		
		try{
		 	imageDescriptorDao.removeDescriptorFromCategories(id, categories);
			return;
		}
		catch (Exception ex){
			System.out.println(ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ;
		}
		
	}

	@PostMapping("/images/galleries")
	public void addGalleryToImage( @RequestParam("id") String id , @RequestBody ArrayList<String> galleries, HttpServletResponse response){
		try{
			imageDescriptorDao.addDescriptorToGalleries(id, galleries);
			return;
		}
		catch (Exception ex){
			System.out.println(ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ;
		}
		
	}

	@DeleteMapping("/images/galleries")
	public void removeGalleriesFromImage( @RequestParam("id") String id , @RequestBody ArrayList<String> galleries, HttpServletResponse response){
		
		try{
		 	imageDescriptorDao.removeDescriptorFromGalleries(id, galleries);
			return;
		}
		catch (Exception ex){
			System.out.println(ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ;
		}
		
	}


}
