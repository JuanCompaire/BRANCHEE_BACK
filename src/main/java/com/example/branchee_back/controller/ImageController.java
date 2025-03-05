package com.example.branchee_back.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/images")//EndPoint 
public class ImageController {

    private final String imageDirectory = "C:/Users/compr/Desktop/BRANCHEE_BACK/uploads/images";

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename){
        try{
            Path imagePath = Paths.get(imageDirectory).resolve(filename).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }
    
}

