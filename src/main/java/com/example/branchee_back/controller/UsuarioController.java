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
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // Mapa para almacenar tokens de sesión en memoria
    private Map<String, String> sessionTokens = new HashMap<>();

    @PostMapping("/signUp")
    public void signUpUser(@RequestBody Usuario user) {
        service.SignUpUser(user);
        System.out.println("SIGNUP : " + user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        System.out.println("Datos recibidos en el backend: " + user);

        boolean success = service.loginUser(user.getEmail(), user.getPassword());

        if (success) {
            String token = UUID.randomUUID().toString();
            sessionTokens.put(token, user.getEmail());

            // Log the generated token
            System.out.println("Generated token: " + token);

            return ResponseEntity.ok(Map.of("token", token));
        } else {
            System.out.println("Login failed for user: " + user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario o contraseña incorrectos"));
        }
    }


    // Endpoint para obtener la información del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String email = sessionTokens.get(token);

        if (email != null) {
            Usuario user = service.findByEmail(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Token inválido o expirado"));
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }
}
