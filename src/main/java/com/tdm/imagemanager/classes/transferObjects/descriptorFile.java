package com.tdm.imagemanager.classes.transferObjects;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tdm.imagemanager.classes.baseApplication.ImageDescriptor;

public class descriptorFile {
    @JsonProperty("descriptor")
    private ImageDescriptor D;
    @JsonProperty("file")
    private Path path;

    descriptorFile(ImageDescriptor d, String base64Image){
        D = d;
        path = imageRaw.decode(base64Image);
    }

    public ImageDescriptor getDescriptor(){
        return D;
    }
    public Path getFilePath(){
        return path;
    }
}
