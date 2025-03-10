package com.example.branchee_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.branchee_back.DTO.TareaDTO;
import com.example.branchee_back.DTO.UsuarioDTO;
import com.example.branchee_back.entity.Proyecto;
import com.example.branchee_back.entity.Tarea;
import java.io.IOException;
import com.example.branchee_back.respository.TareaRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    TareaRepository repository;

    @Transactional
    //Method to create the task
    public void createTarea(TareaDTO taskData,boolean edit) {

        Tarea task;

        if (edit) {
            Optional<Tarea> existingTask = repository.findById(taskData.getTareaId());

            if (existingTask.isPresent()){
                task = existingTask.get();
            } else {
                throw new RuntimeException("La task con ID " + taskData.getTareaId() + " no existe.");
            }
        }
        else {
            task = new Tarea();
        }
        
        task.setId_proyecto(taskData.getId_proyecto());
        task.setName_task(taskData.getName_task());
        task.setDescripcion(taskData.getDescripcion());
        task.setImage(taskData.getImage());
        task.setEstado(taskData.getEstado());
        task.setImportancia(taskData.getImportancia());
        task.setDate_create(taskData.getDate_create());
        task.setUser_id_created_task(taskData.getUser_id_created_task());

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        task.setDate_last_update(formattedDate);

        repository.save(task);  
        
        List<UsuarioDTO> usersData = taskData.getUsuarios();

        List<Integer> usersIdsList = new ArrayList<>();

        for(UsuarioDTO user: usersData){
            usersIdsList.add(user.getUsuarioId());            
        }

        insertTaskUsers(task.getTareaId(), usersIdsList);

    }

    public void insertTaskUsers(Integer taskId, List<Integer>selectedUserIds){ 
        for(Integer userId : selectedUserIds){
            repository.insertTaskUsers(taskId,userId);
        }

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
        return fileName;
    }

    public TareaDTO getTaskById(Integer taskId){

        Map<String, Object> taskData = repository.getTaskById(taskId);

        TareaDTO task = new TareaDTO(
            (Integer) taskData.get("tareaId"),
            (String) taskData.get("name_task"),
            (Integer) taskData.get("id_proyecto"),
            (String) taskData.get("descripcion"),
            (String) taskData.get("image"),
            (String) taskData.get("estado"),
            (String) taskData.get("importancia"),
            (String) taskData.get("date_create"),
            (String) taskData.get("date_last_update"),
            (Integer) taskData.get ("user_id_created_task")     
        );

        List<Map<String, Object>> usersData = repository.getUsersByTaskId(taskId);

        List<UsuarioDTO> users = usersData.stream().map(u ->
                new UsuarioDTO(
                        (Integer) u.get("usuario_id"),
                        (String) u.get("username"),
                        (String) u.get("email")
                )
        ).collect(Collectors.toList());

        task.setUsuarios(users);

        return task;
    }

    public List<TareaDTO> getTasksByUserId (Integer userId){

        List<Map<String, Object>> tasksData = repository.getTasksByUserId(userId);
        System.out.println("Las tasks en forma de Map<Strin,Object> del getTasksByUserId son : "+tasksData);
        
        List<TareaDTO> tasksList = new ArrayList<>();

        for (Map<String, Object> taskData : tasksData){
            tasksList.add(new TareaDTO(
                (Integer) taskData.get("tareaId"),
                (String) taskData.get("name_task"),
                (Integer) taskData.get("id_proyecto")
                )
            );
        }
        return tasksList;
    }

    public void deleteDataLinkToTaskId(Integer taskId){
        repository.deleteUsersFromTask(taskId);
    }
}
