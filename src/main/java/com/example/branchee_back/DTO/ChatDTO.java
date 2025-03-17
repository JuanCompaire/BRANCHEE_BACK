package com.example.branchee_back.DTO;

import com.example.branchee_back.entity.Tarea;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {

    private Integer chatId;
    private String name_tarea;
    private String descripcion;
    private String image;
    private String date_create_chat;
    private Integer user_id_created_chat;
    private Integer id_tarea;

    public ChatDTO(Integer chatId,Integer id_tarea,String name_tarea,String descripcion,
    String image,String date_create_chat,Integer user_id_created_chat){
        this.chatId =chatId;
        this.id_tarea = id_tarea;
        this.name_tarea =name_tarea;
        this.descripcion =descripcion;
        this.image =image;
        this.date_create_chat =date_create_chat;
        this.user_id_created_chat =user_id_created_chat;
    }

    public ChatDTO(){}
}
