package com.example.branchee_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.branchee_back.entity.ProyectRequest;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.service.ProyectoService;

@RestController
@RequestMapping("/api/proyect")
public class ProyectoController {

    @Autowired 
    private ProyectoService service;

    @PostMapping("/create")
    public ResponseEntity<Proyecto> createProyecto(@RequestBody ProyectRequest request) {
        Proyecto proyecto = request.getProyecto();
        List<Integer> selectedUserIds = request.getSelectedUserIds();
        service.createProyecto(proyecto); ;
        service.insertProyectoUseres(proyecto.getId(),selectedUserIds);
        if (proyecto != null) {             
            return ResponseEntity.ok(proyecto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
