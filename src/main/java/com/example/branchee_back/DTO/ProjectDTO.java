package com.example.branchee_back.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class    ProjectDTO {

    private Integer proyectoId;
    private String name_proyect;
    private Integer id_boss;
    private String date_created;
    private List<UsuarioDTO> usuarios;
    private List<TareaDTO> tareas;

    //constructor
    public ProjectDTO(Integer proyectoId, String name_proyect,Integer id_boss,String date_created){
        this.proyectoId = proyectoId;
        this.name_proyect = name_proyect;
        this.id_boss = id_boss;
        this.date_created = date_created;
    }
    
    public ProjectDTO() {
        //TODO Auto-generated constructor stub
    }
    
}
