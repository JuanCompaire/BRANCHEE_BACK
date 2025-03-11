package com.example.branchee_back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.boot.jaxb.hbm.transform.TargetColumnAdapterJaxbColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.branchee_back.DTO.TareaDTO;
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
    public ResponseEntity<Map<String,Object>> createTask(@RequestBody TareaDTO task) {
        System.out.println("El tareaDTO que llega es : "+ task);
        //insert task in BBDD
        service.createTarea(task,false); ;
        if (task != null) {             
            return ResponseEntity.ok(Map.of("message", "Task created successfully", "task", task));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/edit")//EndPoint --> /api/task/edit
    public ResponseEntity<?> editTask(@RequestBody TareaDTO taskData){
        try{
            service.deleteDataLinkToTaskId(taskData.getTareaId());
            service.createTarea(taskData,true);
            return ResponseEntity.ok(Map.of("message", "Task edited successfully", "task", taskData));

        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not edited");
        }
    }

    @PostMapping("/createTaskPhoto")//EndPoint --> /api/task/createTask
    //method to create the task with photo
    public ResponseEntity<TareaDTO> createTaskPhoto(@RequestPart("task") String taskJson,
    @RequestPart("file") MultipartFile file){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            TareaDTO task = objectMapper.readValue(taskJson, TareaDTO.class);
            //save the image in the server
            String imagePath = service.saveImage(file);
            task.setImage(imagePath);

            //insert task in BBDD
            service.createTarea(task,false);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(task);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } 

    @GetMapping("/getTasksByUserId")//EndPoint --> /api/task/getTasksByUserId
    public ResponseEntity<?> getTasksByUserId (@RequestParam Integer userId){
        try{
            List<TareaDTO> taskListByUserId = service.getTasksByUserId(userId);
            return ResponseEntity.ok(taskListByUserId);
        }catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found for this user : "+ e);
        }
    }


    @GetMapping("/getById")//EndPoint --> /api/task/getById
    public ResponseEntity<?> getTaskById(@RequestParam Integer id) {
        try{
            TareaDTO task = service.getTaskById(id);
            return ResponseEntity.ok(task);
        }catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

    

}
