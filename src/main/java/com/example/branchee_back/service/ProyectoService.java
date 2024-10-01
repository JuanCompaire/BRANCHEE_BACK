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
    //Method to create the proyect
    public void createProyecto(Proyecto proyecto) {
        repository.save(proyecto);    
    }
    //Method to insert the users and proyects in their intermediate table.
    public void insertProyectoUseres(Integer proyectoId, List<Integer> selectedUserIds){
        System.out.println("El id del proyecto es : "+ proyectoId);
        for (Integer userId : selectedUserIds){
            System.out.println("Los usuarios selecionados son estos: "+ userId);
            repository.insertProyectoUsers(proyectoId,userId);
        }
    }

    public void editUsersProyect(Integer proyectoId, List<Integer> selectedUserIds){
        System.out.println("El id del proyecto es : "+ proyectoId);
        //logica de borrar todos los usuarios relacionados con ese proyecto
        repository.deleteUsersFromProyect(proyectoId);
        //logica de poner todos los usuarios de la lista que estan relacionados con el proyecto 
        for (Integer userId : selectedUserIds){
            System.out.println("Los usuarios selecionados son estos: "+ userId);
            repository.insertProyectoUsers(proyectoId, userId);

        }
    }

    public List<Proyecto> getProyectsByUserId(Integer id){
        System.out.println("Se llama al getProyectsByUserId del repository con esta id : "+id);
        return repository.getProyectsByUserId(id);
    }

    public Proyecto getProyectoById(Integer id){
        System.out.println("Se llama al getProyectoById del repository con esta id : "+id);
        return repository.getProyectoById(id);
    }

    public Object getAllProjects() {
        return repository.getAllProyectos();
    }
}




