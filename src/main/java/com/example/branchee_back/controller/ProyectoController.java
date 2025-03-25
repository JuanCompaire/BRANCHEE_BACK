package com.example.branchee_back.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.branchee_back.DTO.ProjectDTO;
import com.example.branchee_back.DTO.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.service.ProyectoService;

@RestController
@RequestMapping("/api/proyect")//EndPoint 
public class ProyectoController {

    @Autowired 
    private ProyectoService service;

    @PostMapping("/create")//EndPoint --> /api/proyect/create
    //method to create the proyect and send a message to FrontEnd
    public ResponseEntity<?> createProyecto(@RequestBody ProjectDTO projectData) {
        try{
            service.createProject(projectData,false);
            return ResponseEntity.ok(Map.of("message", "Project created successfully", "project", projectData));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not created");
        }
    }

    @PostMapping("/edit")//EndPoint --> /api/proyect/edit
    public ResponseEntity<?> editProyecto(@RequestBody ProjectDTO projectData){
        try{
            service.deleteDataLinkToProjectId(projectData.getProyectoId()); 
            System.out.println("Se ha hecho el deleteDataLinkToProjectId() en el edit");
            service.createProject(projectData,true);
            System.out.println("Se ha hecho el createProject() en el edit");
            return ResponseEntity.ok(Map.of("message", "Project edited successfully", "project", projectData));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not edited");
        }
    }

    // @PostMapping("/edit")//EndPoint --> /api/proyect/edit
    // public void editProyecto(@RequestBody ProyectRequest request){
    //     Proyecto proyecto = request.getProyecto();
    //     List<Integer> selectedUserIds  = request.getSelectedUserIds();

    //     service.editUsersProyect(proyecto.getProyectoId(),selectedUserIds);
    // }

    //Method to recive the proyects in which the user who log in, is participating
    @GetMapping("/getProyectsByUserId")//EndPoint --> /api/proyect/getProyectsByUserId
    public ResponseEntity<?> getProyectsByUserId(@RequestParam(value = "id", required = true)Integer id){
        System.out.println("Id de usuario para recibir los proyectos en los que participa : "+ id);
        if (id == null || id <= 0) {
            // Suponiendo que quieras devolver algunos proyectos predeterminados o todos los proyectos
            return ResponseEntity.ok(service.getAllProjects()); // Suponiendo que el método getAllProjects exista
        }
        return ResponseEntity.ok(service.getProyectsByUserId(id));
    }

    @GetMapping("/getById")//EndPoint --> /api/proyect/getById
    public ResponseEntity<?> getProyectoById(@RequestParam Integer id) {
        try{
            System.out.println("Al controler de getById ha llegado l siguiente id : "+ id);
            ProjectDTO project = service.getProyectoById(id);
            System.out.println("getProyectoById correct");
            return ResponseEntity.ok(project);
        } catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

}
