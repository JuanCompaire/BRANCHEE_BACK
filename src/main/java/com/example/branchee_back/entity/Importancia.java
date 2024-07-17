package com.example.branchee_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="IMPORTANCIA")
public class Importancia {
    @Id
    @SequenceGenerator(
        name = "importance_sequence",
        sequenceName = "importance_sequence",
        allocationSize = 50
    )
    @GeneratedValue(
        generator = "importance_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String importancia; 

    @OneToOne(mappedBy = "importancia")
    private Tarea tarea;
    
}
    */
