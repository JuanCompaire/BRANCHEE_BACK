package com.example.branchee_back.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.branchee_back.entity.Tarea;

@Repository
public interface TareaRepository  extends JpaRepository<Tarea,Long>{
    
}
