package com.example.branchee_back.entity;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarea_sequence")
    @SequenceGenerator(name = "tarea_sequence", sequenceName = "tarea_sequence", allocationSize = 1)
    private Integer chatId;
    private Integer tareaId;
    private String name_tarea;
    private String descripcion;
    private String image;
    private String date_create_chat;
    private String date_last_update_chat;
    private Integer user_id_created_chat;

    @ManyToOne
    @JoinColumn(name = "id_tarea", insertable = false, updatable = false)
    private Tarea tarea;
    
}
