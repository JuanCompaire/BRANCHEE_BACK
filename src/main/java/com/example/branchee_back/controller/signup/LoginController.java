package com.example.branchee_back.controller.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.service.LoginService;

import java.util.Map;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

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
    
}
