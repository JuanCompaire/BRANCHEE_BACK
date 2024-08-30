package com.example.branchee_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "user_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @Column(
        name = "username",
        nullable = false
    )
    private String username;

    @Column(
        name = "email",
        nullable = false
    )
    private String email;

    @Column(
        name = "password",
        nullable = false
    )
    private String password;

    //relation ManyToMany with Proyect
    @ManyToMany
    @JoinTable(
        name = "usuario_proyecto",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    private Set<Proyecto> proyectos;

   public Integer getId(){
    return id;
   }

   public void setId(Integer id) {
    this.id = id;
    } 

   public String getEmail(){
    return email;
   }

   public void setEmail(String email) {
    this.email = email;
    }   

    public String getUsername(){
        return username;
       }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
       }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
