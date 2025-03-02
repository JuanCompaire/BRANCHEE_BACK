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

    public UsuarioDTO() {
        //TODO Auto-generated constructor stub
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
