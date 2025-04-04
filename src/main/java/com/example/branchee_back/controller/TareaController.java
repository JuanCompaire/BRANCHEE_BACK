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

import com.example.branchee_back.DTO.ChatDTO;
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

    @PostMapping("/create")//EndPoint --> /api/task/create
    public ResponseEntity<TareaDTO> createTask(
        @RequestPart("task") String taskJson,
        @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TareaDTO task = objectMapper.readValue(taskJson, TareaDTO.class);

            // Si hay una imagen, la guardamos y asignamos
            if (file != null && !file.isEmpty()) {
                String imagePath = service.saveImage(file);
                task.setImage(imagePath);
            }

            // Insertamos la tarea en la BBDD
            service.createTarea(task, false);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(task);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/edit")//EndPoint --> /api/task/edit
    public ResponseEntity<?> editTask(
        @RequestPart("task") String taskJson,
        @RequestPart(value = "file", required = false) MultipartFile file) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            TareaDTO task = objectMapper.readValue(taskJson, TareaDTO.class);

            if (file != null && !file.isEmpty() && task.getChats() != null && !task.getChats().isEmpty()) {

                ChatDTO lastChat = task.getChats().get(task.getChats().size() - 1);

                String imagePath = service.saveImage(file);
                
                lastChat.setImage(imagePath);
            }

            service.edit(task);
            return ResponseEntity.ok(Map.of("message", "Task edited successfully", "task", task));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }  
    }

    // @PostMapping("/editNoComments")//EndPoint --> /api/task/editNoComments
    // public ResponseEntity<?> editTaskNoComments(@RequestBody TareaDTO taskData){
    //     try{
    //         service.deleteDataLinkToTaskId(taskData.getTareaId());
    //         service.createTarea(taskData,true);
    //         return ResponseEntity.ok(Map.of("message", "Task edited successfully", "task", taskData));

    //     } catch (RuntimeException e){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not edited");
    //     }
    // }

    @GetMapping("/getTasksByUserId")//EndPoint --> /api/task/getTasksByUserId
    public ResponseEntity<?> getTasksByUserId (@RequestParam Integer userId){
        try{
            List<TareaDTO> taskListByUserId = service.getTasksByUserId(userId);
            return ResponseEntity.ok(taskListByUserId);
        }catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found for this user : "+ e);
        }
    }

    @GetMapping("/getTasksByUserIdAllDetails")//EndPoint --> /api/task/getTasksByUserIdAllDetails
    public ResponseEntity<?> getTasksByUserIdAllDetails(@RequestParam(value = "id", required = true)Integer id){
        try{
            List<TareaDTO> tasks = service.getTasksByIdAllDetails(id);
            return ResponseEntity.ok(tasks);

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project/s not found");
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
