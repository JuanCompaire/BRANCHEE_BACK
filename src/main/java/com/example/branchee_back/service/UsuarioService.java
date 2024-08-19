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

    public boolean loginUser(String email, String password) {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        Optional<Usuario> user = repository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get());
            return true;
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    @Transactional
    public void SignUpUser(Usuario user) {
        repository.save(user);
    }

    public Object getUsers() {
        return repository.findAll();
    }

    // Nuevo método para encontrar un usuario por su email
    public Usuario findByEmail(String email) {
        Optional<Usuario> user = repository.findByEmail(email);
        return user.orElse(null);
    }
}
