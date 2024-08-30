package com.example.branchee_back.respository;

import java.beans.Transient;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.branchee_back.entity.Proyecto;

import jakarta.transaction.Transactional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Long>{

    @Modifying
    @Transactional
    @Query(value = "insert into usuario_proyecto (usuario_id,proyecto_id) values(:user_id, :proyecto_id)", nativeQuery = true)
    void insertProyectoUsers(@Param("proyecto_id")Integer id_proyect,@Param("user_id")Integer id_user);
   
}
