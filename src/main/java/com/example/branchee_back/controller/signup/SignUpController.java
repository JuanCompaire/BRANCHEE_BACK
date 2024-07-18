package com.example.branchee_back.controller.signup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.branchee_back.entity.Usuario;

@RestController
public class SignUpController {

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Usuario user) {
        // Implementa la lógica para procesar el usuario recibido
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado correctamente");
        System.out.println("SIGNUP :"+ user);
        return ResponseEntity.ok(response);
    }
    
}
