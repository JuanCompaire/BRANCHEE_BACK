package com.example.branchee_back.entity;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "id_proyecto") // Nombre de la columna en la tabla tarea que referencia al proyecto
    private Proyecto proyecto;
    
    private String descripcion;
    private String image;

    @OneToMany(mappedBy = "tarea")
    private Set<Usuario> usuarios;

    @OneToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "id_importancia", referencedColumnName = "id")
    private Importancia importancia;

}
*/