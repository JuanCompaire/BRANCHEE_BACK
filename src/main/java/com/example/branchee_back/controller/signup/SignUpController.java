package com.example.branchee_back.controller.signup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.service.SignUpService;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping
    public void signUpUser(@RequestBody Usuario user){
        service.SignUpUser(user);
        System.out.println("SIGNUP : "+ user);
    }
    
}
