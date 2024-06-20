package com.example.branchee_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "USUARIO",
    uniqueConstraints = @UniqueConstraint(
        name = "email_unique",
        columnNames = "email"
    )
)
public class Usuario {

    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 50
    )
    @GeneratedValue(
        generator = "user_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    private String username;

    @Column(
        name = "email",
        nullable = false
    )
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
        name = "usuario_proyecto",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    private Set<Proyecto> proyectos;

    
}
