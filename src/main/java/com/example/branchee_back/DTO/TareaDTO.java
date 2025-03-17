package com.example.branchee_back.DTO;
import java.util.List;

import com.example.branchee_back.entity.Chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TareaDTO {

    private Integer tareaId;
    private Integer id_proyecto;
    private String name_task;
    private String descripcion;
    private String image;
    private String estado;
    private String importancia;
    private String date_create;
    private String date_last_update;
    private Integer user_id_created_task;
    private List<UsuarioDTO> usuarios;
    private List<ChatDTO> chats;


    public TareaDTO(Integer tareaId,String name_task,Integer id_proyecto,String descripcion,
                    String image,String estado,String importancia,String date_create,String date_last_update,
                    Integer user_id_created_task ){
        this.tareaId =tareaId;
        this.id_proyecto =id_proyecto;
        this.name_task =name_task;
        this.descripcion =descripcion;
        this.image =image;
        this.estado =estado;
        this.importancia =importancia;
        this.date_create =date_create; 
        this.date_last_update =date_last_update;
        this.user_id_created_task = user_id_created_task;
    }

    public TareaDTO(Integer tareaId,String name_task){
        this.tareaId =tareaId;
        this.name_task =name_task;
    }

    public TareaDTO(Integer tareaId,String name_task,String estado ){
        this.tareaId =tareaId;
        this.name_task =name_task;
        this.estado =estado;
    }

    public TareaDTO(Integer tareaId,String name_task,Integer id_proyecto){
        this.tareaId =tareaId;
        this.name_task =name_task;
        this.id_proyecto = id_proyecto;
    }

    public TareaDTO(){
        
    }

}
