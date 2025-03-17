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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_sequence")
    @SequenceGenerator(name = "chat_sequence", sequenceName = "chat_sequence", allocationSize = 1)
    private Integer chatId;
    private String descripcion;
    private String image;
    private String date_create_chat;
    private Integer user_id_created_chat;
    private Integer id_tarea;
    
}
