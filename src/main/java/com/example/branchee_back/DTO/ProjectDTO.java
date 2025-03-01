package com.example.branchee_back.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectDTO {

    private Integer proyectoId;
    private String name_proyect;
    private Integer id_boss;
    private LocalDateTime dateCreate;
    private List<UsuarioDTO> usuarios;
    private List<TareaDTO> tareas;

    //constructor
    public ProjectDTO(Integer proyectoId, String name_proyect,Integer id_boss,LocalDateTime dateCreate){
        this.proyectoId = proyectoId;
        this.name_proyect = name_proyect;
        this.id_boss = id_boss;
        this.dateCreate = dateCreate;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getName_proyect() {
        return name_proyect;
    }

    public void setName_proyect(String name_proyect) {
        this.name_proyect = name_proyect;
    }

    public Integer getId_boss() {
        return id_boss;
    }

    public void setId_boss(Integer id_boss) {
        this.id_boss = id_boss;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<TareaDTO> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaDTO> tareas) {
        this.tareas = tareas;
    }
}
