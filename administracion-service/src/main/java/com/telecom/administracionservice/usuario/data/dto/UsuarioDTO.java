package com.telecom.administracionservice.usuario.data.dto;

import com.telecom.administracionservice.rol.data.read.RolRead;

import java.util.List;

public class UsuarioDTO {

    private String nombre;


    private String password;

    private List<RolRead> roles;

    public UsuarioDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolRead> getRoles() {
        return roles;
    }

    public void setRoles(List<RolRead> roles) {
        this.roles = roles;
    }
}
