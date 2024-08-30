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

    //Method to login the user
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

    //Method to register the user
    @Transactional
    public void SignUpUser(Usuario user) {
        repository.save(user);
    }

    //Method to get all the users
    public Object getUsers() {
        return repository.findAll();
    }

    //Method to find a user by the email
    public Usuario findByEmail(String email) {
        Optional<Usuario> user = repository.findByEmail(email);
        return user.orElse(null);
    }
}
