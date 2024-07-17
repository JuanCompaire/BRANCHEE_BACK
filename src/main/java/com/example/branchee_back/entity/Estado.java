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
@Table(name="ESTADO")
public class Estado {
    @Id
    @SequenceGenerator(
        name = "state_sequence",
        sequenceName = "state_sequence",
        allocationSize = 50
    )
    @GeneratedValue(
        generator = "state_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String estado;

    @OneToOne(mappedBy = "estado")
    private Tarea tarea;
    
}

*/