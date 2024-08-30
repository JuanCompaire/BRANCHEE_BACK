package com.example.branchee_back.entity;

import java.util.Date;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PROYECTO")

public class Proyecto {

    @Id
    @SequenceGenerator(
        name = "proyect_sequence",
        sequenceName = "proyect_sequence",
        allocationSize = 50
    )
    @GeneratedValue(
        generator = "proyect_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String name;
    private Integer id_boss;
    private String date_create;

    //relation ManyToMany with user
    @ManyToMany(mappedBy = "proyectos")
    private Set<Usuario> usuarios;

}

