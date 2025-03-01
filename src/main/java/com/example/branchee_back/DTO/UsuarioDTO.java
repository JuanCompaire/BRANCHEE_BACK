package com.example.branchee_back.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO{

    private Integer usuarioId;
    private String username;
    private String email;

    public  UsuarioDTO(Integer usuarioId,String username,String email){
        this.usuarioId = usuarioId;
        this.username = username;
        this.email = email;

    }

}
