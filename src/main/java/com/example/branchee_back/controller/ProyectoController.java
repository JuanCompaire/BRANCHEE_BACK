package com.example.branchee_back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.branchee_back.entity.ProyectRequest;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.service.ProyectoService;

@RestController
@RequestMapping("/api/proyect")//EndPoint 
public class ProyectoController {

    @Autowired 
    private ProyectoService service;
    
    @PostMapping("/create")//EndPoint --> /api/proyect/create
    //method to create the proyect and send a message to FrontEnd
    public ResponseEntity<Proyecto> createProyecto(@RequestBody ProyectRequest request) {
        //convert the ProyectDTO into Proyect class with the data from FrontEnd
        Proyecto proyecto = request.getProyecto();
        //save in a list the users IDs,which are in the proyect
        List<Integer> selectedUserIds = request.getSelectedUserIds();
        //insert proyect in BBDD
        service.createProyecto(proyecto); ;
        //insert users and proyect in intermediate table
        service.insertProyectoUseres(proyecto.getId(),selectedUserIds);
        // to send a message to FrontEnd
        if (proyecto != null) {             
            return ResponseEntity.ok(proyecto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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

    @GetMapping("/getProyectoById")//EndPoint --> /api/proyect/getProyectoById
    public ResponseEntity<?> getProyectoById(@RequestParam(value = "id", required = true)Integer id){
        System.out.println("Id del proyecto a buscar : "+ id);
        if (id == null || id <= 0) {
            // Suponiendo que quieras devolver algunos proyectos predeterminados o todos los proyectos
            return ResponseEntity.ok(service.getAllProjects()); // Suponiendo que el método getAllProjects exista
        }
        return ResponseEntity.ok(service.getProyectoById(id)); 

    }
}
