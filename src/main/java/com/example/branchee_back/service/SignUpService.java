package com.example.branchee_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.branchee_back.entity.Usuario;
import com.example.branchee_back.respository.SignUpRepository;


@Service
public class SignUpService {

    @Autowired
    private SignUpRepository repository;

    @Transactional
    public void SignUpUser(Usuario user){
        repository.save(user);

    }
    
}
