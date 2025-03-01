package com.example.branchee_back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.branchee_back.entity.ProyectRequest;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.entity.Tarea;
import com.example.branchee_back.service.ProyectoService;
import com.example.branchee_back.service.TareaService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/task")//EndPoint 
public class TareaController {

    @Autowired 
    private TareaService service;

    @PostMapping("/createTask")//EndPoint --> /api/task/createTask
    //method to create the task
    public ResponseEntity<Tarea> createTask(@RequestBody Tarea tarea) {
        //convert the ProyectDTO into Proyect class with the data from FrontEnd
        Tarea task = tarea;
        //insert task in BBDD
        service.createTarea(task); ;
        if (task != null) {             
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/createTaskPhoto")//EndPoint --> /api/task/createTask
    //method to create the task with photo
    public ResponseEntity<Tarea> createTaskPhoto(@RequestPart("task") String taskJson,
    @RequestPart("file") MultipartFile file){

        try{
            //read the json and convert it into a task
            ObjectMapper objectMapper = new ObjectMapper();
            Tarea task = objectMapper.readValue(taskJson, Tarea.class);

            //save the image in the server
            String imagePath = service.saveImage(file);
            task.setImage(imagePath);

            //insert task in BBDD
            service.createTarea(task);


            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(task);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}
