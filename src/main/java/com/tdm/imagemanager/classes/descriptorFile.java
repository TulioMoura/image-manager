package com.tdm.imagemanager.classes;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonProperty;

public class descriptorFile {
    @JsonProperty("descriptor")
    private ImageDescriptor D;
    @JsonProperty("file")
    private Path path;

    descriptorFile(ImageDescriptor d, String base64Image){
        D = d;
        path = image_raw.decode(base64Image);
    }

    public ImageDescriptor getDescriptor(){
        return D;
    }
    public Path getFilePath(){
        return path;
    }
}
