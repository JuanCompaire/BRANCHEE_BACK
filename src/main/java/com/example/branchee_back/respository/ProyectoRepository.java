package com.example.branchee_back.respository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.branchee_back.entity.Proyecto;

import jakarta.transaction.Transactional;

@Repository
//JPA Proyect Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Integer>{


    @Modifying
    @Transactional
    //Postgres Query to insert the users and proyects in their intermediate table.
    @Query(value = "insert into usuario_proyecto (usuario_id,proyecto_id) values(:user_id, :proyecto_id)", nativeQuery = true)
    void insertProyectoUsers(@Param("proyecto_id")Integer id_proyect,@Param("user_id")Integer id_user);

    @Transactional
    @Query(value ="select p.*\r\n" + //
                "from usuario u inner join usuario_proyecto up on u.usuario_id  = up.usuario_id \r\n" + //
                "inner join proyecto p on up.proyecto_id = p.proyecto_id  \r\n" + //
                "where u.usuario_id  = :id\r\n" + //
                "union ALL\r\n" + //
                "select p.*\r\n" + //
                "from proyecto p\r\n" + //
                "where id_boss = :id;",nativeQuery = true)
    List<Proyecto> getProyectsByUserId(@Param("id")Integer id);

    @Transactional
    @Query(value = "select u.usuario_id ,u.username,u.email\n" +
            "from proyecto p inner join usuario_proyecto up on p.proyecto_id  =up.proyecto_id \n" +
            "inner join usuario u on up.usuario_id = u.usuario_id \n" +
            "where p.proyecto_id  = :id ;\n", nativeQuery = true)
    List<Map<String, Object>> getUsersByProyectId(@Param("id") Integer id);

    @Transactional
    @Query(value = "select t.tarea_id, t.name_task, t.estado \n" +
            "from proyecto p inner join tarea t on p.proyecto_id = t.id_proyecto \n" +
            "where p.proyecto_id = :id;", nativeQuery = true)
    List<Map<String, Object>> getTasksFromProjectId(@Param("id") Integer id);

    @Transactional
    @Query(value = "select * from proyecto p where proyecto_id = :id;",
    nativeQuery = true)
    Map<String, Object> getProyectoById(@Param("id")Integer id);

    @Transactional
    @Query(value = "select * from proyecto;",nativeQuery = true)
    List<Proyecto> getAllProyectos();

    @Modifying
    @Transactional
    @Query(value = "delete from proyecto p where p.proyecto_id = :id",nativeQuery = true)
    void deleteProjectById(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from usuario_proyecto up where up.proyecto_id = :id",nativeQuery = true)
    void deleteUsersFromProyect(@Param("id")Integer id);
   
}
