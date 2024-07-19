package com.example.branchee_back.respository;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.branchee_back.entity.Usuario;

public interface SignUpRepository  extends JpaRepository<Usuario,Long>{

    

    
    
}
