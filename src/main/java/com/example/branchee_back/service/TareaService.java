package com.example.branchee_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.branchee_back.entity.Tarea;
import java.io.IOException;
import com.example.branchee_back.respository.TareaRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TareaService {

    @Autowired
    TareaRepository repository;

    @Transactional
    //Method to create the task
    public void createTarea(Tarea task) {
        repository.save(task);   
    }

    //Route to save the image
    private final String UPLOAD_DIR = "uploads/images/";

    //Method to save the image
    public String saveImage(MultipartFile file) throws IOException{

        //Create the directory if it does not exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        //Get the file name of the image
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        //To get the path with the file name and the route
        Path filePath = uploadPath.resolve(fileName);
        //Save the image in the server
        Files.write(filePath, file.getBytes());

        //Return the path of the image
        return UPLOAD_DIR + fileName;

    }
}
