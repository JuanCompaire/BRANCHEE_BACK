package com.example.branchee_back.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.branchee_back.entity.Usuario;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
//JPA User Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByEmailAndPassword(String email, String password);
    Usuario findByEmail(String email);

    @Transactional(readOnly = true)
    @Query(value = "SELECT u.* FROM usuario u WHERE u.username LIKE CONCAT(:string, '%')", nativeQuery = true)
    List<Usuario> getUsersByString(@Param("string") String string);

}
