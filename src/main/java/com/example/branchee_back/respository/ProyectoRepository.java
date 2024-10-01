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
//JPA Proyect Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Long>{

    @Modifying
    @Transactional
    //Postgres Query to insert the users and proyects in their intermediate table.
    @Query(value = "insert into usuario_proyecto (usuario_id,proyecto_id) values(:user_id, :proyecto_id)", nativeQuery = true)
    void insertProyectoUsers(@Param("proyecto_id")Integer id_proyect,@Param("user_id")Integer id_user);

    @Transactional
    @Query(value = "select p.* \r\n" + //
                "from usuario u inner join usuario_proyecto up on u.id = up.usuario_id inner join proyecto p on up.proyecto_id = p.id \r\n" + //
                "where u.id = :id \r\n" + //
                "union ALL\r\n" + //
                "select p.*\r\n" + //
                "from proyecto p \r\n" + //
                "where id_boss = :id\r\n" + //
                ";"+ " ;",nativeQuery = true)
    List<Proyecto> getProyectsByUserId(@Param("id")Integer id);

    @Transactional
    @Query(value = "select * from  proyecto  where id = :id"+ " ;",nativeQuery = true)
    Proyecto getProyectoById(@Param("id")Integer id);

    @Transactional
    @Query(value = "select * from proyecto;",nativeQuery = true)
    List<Proyecto> getAllProyectos();

    @Modifying
    @Transactional
    @Query(value = "delete from usuario_proyecto up where up.proyecto_id = :id",nativeQuery = true)
    void deleteUsersFromProyect(@Param("id")Integer id);
   
}
