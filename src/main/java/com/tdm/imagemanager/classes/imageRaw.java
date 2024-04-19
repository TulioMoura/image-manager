package com.tdm.imagemanager.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
class imageRaw {
    static Path decode(String encodedImage){
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
        Path temporaryPath = Paths.get("tmp/");
        return Files.write(temporaryPath,decodedBytes, StandardOpenOption.CREATE_NEW);
        }
        catch(IOException ex){
            System.out.println("cannot write file");
            System.out.println(ex);
            return null;
        }
    }    
}
