package com.telecom.authservice.data.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_UserCredential")
public class UserCredential implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_iden")
    private Integer id;
    @Column(name = "usu_nick", nullable = false, unique = true, length = 250)
    private String nombre;
    @Column(name = "usu_email", nullable = false, unique = true, length = 250)
    private String email;
    @Column(name = "usu_pass", nullable = false, length = 150)
    private String password;

    public UserCredential() {
    }

    public UserCredential(Integer id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
