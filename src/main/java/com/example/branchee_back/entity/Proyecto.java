package com.example.branchee_back.entity;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;
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
@Table(name="PROYECTO")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proyect_sequence")
    @SequenceGenerator(name = "proyect_sequence", sequenceName = "proyect_sequence", allocationSize = 1)
    private Integer proyectoId;

    private String name_proyect;
    private Integer id_boss;

    @Column(name = "date_created")
    private String dateCreate;

    // Relación ManyToMany con usuario
    @ManyToMany(mappedBy = "proyectos", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios;

    // Relación OneToMany con tareas
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tarea> tareas;

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getName_proyect() {
        return name_proyect;
    }

    public void setName_proyect(String name_proyect) {
        this.name_proyect = name_proyect;
    }

    public Integer getId_boss() {
        return id_boss;
    }

    public void setId_boss(Integer id_boss) {
        this.id_boss = id_boss;
    }


    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }
}

