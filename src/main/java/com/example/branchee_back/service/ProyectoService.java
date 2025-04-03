package com.example.branchee_back.service;

import com.example.branchee_back.DTO.ProjectDTO;
import com.example.branchee_back.DTO.TareaDTO;
import com.example.branchee_back.DTO.UsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

    public void createProject (ProjectDTO projectData,boolean edit){
        Proyecto project;

        if (edit){
            Optional<Proyecto> existingProject = repository.findById(projectData.getProyectoId());

            if(existingProject.isPresent()){
                project = existingProject.get();
            } else {
                throw new RuntimeException("El proyecto con ID " + projectData.getProyectoId() + " no existe.");
            }
        } else {
            project = new Proyecto();
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            project.setDateCreate(formattedDate);
        }
        
        project.setName_proyect(projectData.getName_proyect());
        project.setId_boss((projectData.getId_boss()));

        System.out.println("este es el project que se va a crear/update al repository : "+ project);
        repository.save(project);
        System.out.println("se ha creado un project con estos datos: "+ project);

        List<UsuarioDTO> usersData = projectData.getUsuarios();
        
        List<Integer> usersIdsList = new ArrayList<>();

        for(UsuarioDTO user : usersData){
            usersIdsList.add(user.getUsuarioId());
        }

        System.out.println("los ids de la userIdsList son : "+usersIdsList);
        insertProyectoUseres(project.getProyectoId(), usersIdsList);
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

    public List<ProjectDTO> getProyectoByIdAllDetails(Integer id){

        List<Proyecto> proyectosBase = repository.getProyectsByUserId(id);
    
        return proyectosBase.parallelStream() 
            .map(proyecto -> getProyectoById(proyecto.getProyectoId()))
            .collect(Collectors.toList());

    }

    public ProjectDTO getProyectoById (Integer projectId){
        //obtain project data
        System.out.println("La id que llega al getProyectoById service es : "+ projectId);
        Map<String, Object> projectData = repository.getProyectoById(projectId);
        System.out.println("Despues del repository el getProyectoById es : "+
        projectData.get("proyecto_id")+ " "+projectData.get("name_proyect")+
         " "+projectData.get("id_boss")+ " "+projectData.get("date_created"));
        if (projectData == null){
            System.out.println("El project da problemas y tiene esta ID : "+ projectData);
            throw new RuntimeException("Proyecto no encontrado");
        }

        //create the project DTO
        ProjectDTO project = new ProjectDTO(
                (Integer)  projectData.get("proyecto_id"),
                (String) projectData.get("name_proyect"),
                (Integer)  projectData.get("id_boss"),
                (String)projectData.get("date_created")    
        );
        
        
        //get the users list
        List<Map<String, Object>> usersData = repository.getUsersByProyectId(projectId);

        List<UsuarioDTO> users = usersData.stream().map(u ->
                new UsuarioDTO(
                        (Integer) u.get("usuario_id"),
                        (String) u.get("username"),
                        (String) u.get("email")
                )
        ).collect(Collectors.toList());

        //get the task list
        List<Map<String, Object>> tasksData = repository.getTasksFromProjectId(projectId);

        List<TareaDTO> tasks = tasksData.stream().map(t ->
                new TareaDTO(
                        (Integer) t.get("tarea_id"),
                        (String) t.get("name_task"),
                        (String) t.get("estado")
                )
        ).collect(Collectors.toList());


        project.setUsuarios(users);
        project.setTareas(tasks);

        return project;
    }

    public String getNameProjectById(Integer projectId){
        return repository.getNameProjectById(projectId);
    }

    public void deleteDataLinkToProjectId(Integer projectId){
        System.out.println("El id del proyecto_usuarios que se va a eliminar es : "+projectId);
        repository.deleteUsersFromProyect(projectId);
        //repository.deleteProjectById(projectId);
    }


    public Object getAllProjects() {
        return repository.getAllProyectos();
    }
}




