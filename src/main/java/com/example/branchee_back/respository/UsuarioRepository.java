package com.example.branchee_back.respository;

import com.example.branchee_back.DTO.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.branchee_back.entity.Usuario;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
//JPA User Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByEmailAndPassword(String email, String password);
    Usuario findByEmail(String email);

    @Transactional(readOnly = true)
    @Query(value = "SELECT u.* FROM usuario u WHERE u.username LIKE CONCAT(:string, '%')", nativeQuery = true)
    List<Usuario> getUsersByString(@Param("string") String string);

    @Transactional
    @Query(value = "select u.usuario_id ,u.username,u.email\n" +
            "from proyecto p inner join usuario_proyecto up on p.proyecto_id  =up.proyecto_id \n" +
            "inner join usuario u on up.usuario_id = u.usuario_id \n" +
            "where p.proyecto_id  = :id ;\n", nativeQuery = true)
    List<Map<String, Object>> getUsersByProyectId(@Param("id") Integer id);

    @Transactional
    @Query(value = "select u.* from usuario u where u.usuario_id = :id ;", nativeQuery = true)
    Usuario findById(@Param("id") Integer id);

}
