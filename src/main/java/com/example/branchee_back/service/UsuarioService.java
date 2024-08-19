package com.example.branchee_back.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.respository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public boolean loginUser(String email, String password){
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        Optional<Usuario> user = repository.findByEmailAndPassword(email, password);
        return user.isPresent();
    }

    @Transactional
    public void SignUpUser(Usuario user){
        repository.save(user);

    }


    public Object getUsers() {
        return repository.findAll();
    }
}
