package com.tdm.imagemanager.utils;

public class Utils {
    public static String getFileExtension(String filename){
        String[] filenameSplitted = filename.split("\\.");
		String fileExtension = filenameSplitted[filenameSplitted.length -1 ];
        return fileExtension;
    }
}
