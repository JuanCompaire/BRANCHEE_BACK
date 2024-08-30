package com.example.branchee_back.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.respository.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    ProyectoRepository repository;

    @Transactional
    public void createProyecto(Proyecto proyecto) {
        repository.save(proyecto);    
        
    }

    public void insertProyectoUseres(Integer proyectoId, List<Integer> selectedUserIds){
        System.out.println("El id del proyecto es : "+ proyectoId);
        for (Integer userId : selectedUserIds){
            System.out.println("Los usuarios selecionados son estos: "+ userId);
            repository.insertProyectoUsers(proyectoId,userId);

        }
    }

}




