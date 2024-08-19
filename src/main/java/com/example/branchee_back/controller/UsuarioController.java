package com.example.branchee_back.controller;

import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @RequestMapping("/signUp")
    @PostMapping
    public void signUpUser(@RequestBody Usuario user){
        service.SignUpUser(user);
        System.out.println("SIGNUP : "+ user);
    }

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        System.out.println("Datos recibidos en el backend: " + user); // Imprimir datos recibidos

        boolean success = service.loginUser(user.getEmail(), user.getPassword());

        if (success) {
            return ResponseEntity.ok(Map.of("message", "Login exitoso"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario o contraseña incorrectos"));
        }
    }

    @RequestMapping("/getUsers")
    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }
}
