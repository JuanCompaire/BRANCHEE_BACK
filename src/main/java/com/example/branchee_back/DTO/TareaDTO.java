package com.example.branchee_back.DTO;
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

    public TareaDTO(Integer tareaId,String name_task,Integer id_proyecto,String descripcion,
                    String image,String estado,String importancia,String date_create,String date_last_update){
        this.tareaId =tareaId;
        this.id_proyecto =id_proyecto;
        this.name_task =name_task;
        this.descripcion =descripcion;
        this.image =image;
        this.estado =estado;
        this.importancia =importancia;
        this.date_create =date_create;
        this.date_last_update =date_last_update;
    }

    public TareaDTO(Integer tareaId,String name_task){
        this.tareaId =tareaId;
        this.name_task =name_task;
    }

}
