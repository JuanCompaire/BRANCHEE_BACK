package com.example.branchee_back.respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.branchee_back.entity.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByEmailAndPassword(String email,String password);
    
}
