package com.example.branchee_back.respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.branchee_back.entity.Tarea;

import jakarta.transaction.Transactional;

@Repository
public interface TareaRepository  extends JpaRepository<Tarea,Integer>{

    @Transactional
    @Query(value = "select * from tarea a where tarea_id = :id;",
    nativeQuery = true)
    Map<String, Object> getTaskById(@Param("id")Integer id);

    @Transactional
    @Query(value = "select t.*\r\n" + //
                "from usuario u inner join usuario_proyecto up on u.usuario_id = up.usuario_id \r\n" + //
                "inner join proyecto p on up.proyecto_id = p.proyecto_id \r\n" + //
                "inner join tarea t on t.id_proyecto = p.proyecto_id \r\n" + //
                "where u.usuario_id = :id;",
    nativeQuery = true)
    List<Map<String, Object>> getTasksByUserId(@Param("id")Integer id);




    
}
