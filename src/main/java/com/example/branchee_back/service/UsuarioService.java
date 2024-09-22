package com.example.branchee_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.respository.UsuarioRepository;
import com.github.javafaker.Faker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    //to use faker dependecy to create fake data
    Faker faker = new Faker();

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
        /* to fake data in users table
        for (int i=0;i< 50;i++){
           Usuario newUser = new Usuario();
           newUser.setUsername(faker.name().fullName());
           newUser.setEmail(faker.name().username() + "@gmail.com");
           newUser.setPassword(faker.lorem().characters(8,16));
            repository.save(newUser);
        }*/
    }

    //Method to get all the users
    public Object getUsers() {
        return repository.findAll();
    }
    
    public Object getUser(Integer id){
        return repository.findById(id);
    }

    //Method to recibe a list of user which contains a specific string
    public Object getUsersByString(String string){
        System.out.println("El string que llega al repository es : "+string);
        return repository.getUsersByString(string); 
    }

    public Object getUsersByProyectId(Integer id){
        System.out.println("El id del proyecto para conseguir los usuarios es : "+id);
        return repository.getUsersByProyectId(id);
    }

    public Usuario findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    /*Method to find a user by the email
    public Usuario findByEmail(String email) {
        Optional<Usuario> user = repository.findByEmail(email);
        return user.orElse(null);
    }
        */
}
