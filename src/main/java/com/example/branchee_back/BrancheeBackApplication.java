package com.example.branchee_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200") // Habilitar CORS globalmente
public class BrancheeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrancheeBackApplication.class, args);
	}

}
