package com.example.branchee_back.entity;

import java.util.Set;
import jakarta.persistence.Entity;
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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAREA")
public class Tarea {

    @Id
    @SequenceGenerator(
        name = "tarea_sequence",  // Corregido el nombre del generador
        sequenceName = "tarea_sequence",
        allocationSize = 50
    )
    @GeneratedValue(
        generator = "tarea_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private Integer id_proyecto;
    private String descripcion;
    private String image;
    private String estado;
    private String importancia;

    //relation ManyToMany with Proyect
    @ManyToMany
    @JoinTable(
        name = "tarea_proyecto",
        joinColumns = @JoinColumn(name = "tarea_id"),
        inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    private Set<Proyecto> proyectos;

}
