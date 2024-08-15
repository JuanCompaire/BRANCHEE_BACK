package com.example.branchee_back.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.respository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;

    public boolean loginUser(String email, String password){
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        Optional<Usuario> user = repository.findByEmailAndPassword(email, password);
        return user.isPresent();
    }
    
}
