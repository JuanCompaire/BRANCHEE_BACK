package com.example.branchee_back.controller;

import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")//EndPoint 
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Map to save sesion tokens in memory
    private Map<String, String> sessionTokens = new HashMap<>();

    @PostMapping("/signUp")//EndPoint --> /api/auth/signUp
    //Method to create an user
    public void signUpUser(@RequestBody Usuario user) {
        //insert the user in BBDD
        service.SignUpUser(user);
        System.out.println("SIGNUP : " + user);
    }

    @PostMapping("/login")//EndPoint --> /api/auth/login
    //Method to login an user
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        System.out.println("Datos recibidos en el backend: " + user);
        //variable to chekc if the user is in BBDD
        boolean success = service.loginUser(user.getEmail(), user.getPassword());
        //if the user exists in BBDD
        if (success) {
            //generate a token for the sesion
            String token = UUID.randomUUID().toString();
            //set the token to the user
            sessionTokens.put(token, user.getEmail());
            // Log the generated token
            System.out.println("Generated token: " + token);
            //return message of the token created
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            //return a message that the user is not registered.
            System.out.println("Login failed for user: " + user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario o contraseña incorrectos"));
        }
    }

    @GetMapping("/getUsers")//EndPoint --> /api/auth/getUsers
    //Method to recive the list of all users
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }
}
