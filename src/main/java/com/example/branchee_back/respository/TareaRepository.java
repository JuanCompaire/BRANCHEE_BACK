package com.example.branchee_back.respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.branchee_back.DTO.ChatDTO;
import com.example.branchee_back.DTO.TareaDTO;
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

    @Modifying
    @Transactional
    //Postgres Query to insert the users and proyects in their intermediate table.
    @Query(value = "insert into usuario_tarea (usuario_id,tarea_id) values(:userId, :taskId)", nativeQuery = true)
    void insertTaskUsers(@Param("taskId")Integer taskId,@Param("userId")Integer userId);

    @Modifying
    @Transactional
    @Query(value = "insert into chat (chat_id,tarea_id,name_tarea,descripcion,image,date_create_chat,user_id_created_chat) \r\n" + //
                "values (DEFAULT, :tarea_id,:name_tarea,:descripcion,:image,:date_create_chat,:user_id_created_chat);", nativeQuery = true)
    void insertChat(@Param("tarea_id")Integer tarea_id,
                    @Param("name_tarea")String name_tarea,
                    @Param("descripcion")String descripcion,
                    @Param("image")String image,
                    @Param("date_create_chat")String date_create_chat,
                    @Param("user_id_created_chat")Integer user_id_created_chat);

    @Transactional
    @Query(value = "select u.usuario_id ,u.username ,u.email \r\n" + //
                "from usuario u inner join usuario_tarea ut on u.usuario_id = ut.usuario_id \r\n" + //
                "inner join tarea t on ut.tarea_id = t.tarea_id \r\n" + //
                "where t.tarea_id = :id;",
    nativeQuery = true)
    List<Map<String, Object>> getUsersByTaskId(@Param("id")Integer id);

    @Transactional
    @Query(value = "select c.*\r\n" + //
                "from tarea t inner join chat c on c.id_tarea = t.tarea_id \r\n" + //
                "where t.tarea_id = :id;",
    nativeQuery = true)
    List<Map<String, Object>> getChatsByTaskId(@Param("id")Integer id);


    @Modifying
    @Transactional
    @Query(value = "delete from usuario_tarea ut where ut.tarea_id = :id",nativeQuery = true)
    void deleteUsersFromTask(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete \r\n" + //
                "from chat c\r\n" + //
                "where c.id_tarea = :id;",nativeQuery = true)
    void deleteChatLinkToTaskId(@Param("id")Integer id);




    
}
