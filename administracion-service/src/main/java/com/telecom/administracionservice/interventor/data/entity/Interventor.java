package com.telecom.administracionservice.interventor.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_Interventor")
public class Interventor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_iden", nullable = false)
    private Integer iden;

    @Column(name = "int_codi", nullable = false, unique = true, length = 13)
    private String codigo;

    @Column(name = "int_nomb", length = 50)
    private String nombre;

    @Column(name = "int_apel", nullable = false, length = 100)
    private String apellido;

    @Column(name = "int_dire", length = 100)
    private String direccion;

    @Column(name = "int_mail", length = 200)
    private String email;

    @Column(name = "int_fono", length = 10)
    private String telefono;

    @Column(name = "int_refe")
    private Integer referencia;

    @Column(name = "int_fech_naci")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "int_name_img")
    private String nombreImagen;

    @Column(name = "int_img")
    private String imagen;

    @Column(name = "int_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "int_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "int_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    public Interventor() {
    }

    public Interventor(Integer iden, String codigo, String nombre, String apellido, String direccion, String email, String telefono, Integer referencia, Date fechaNacimiento, String nombreImagen, String imagen, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.iden = iden;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.referencia = referencia;
        this.fechaNacimiento = fechaNacimiento;
        this.nombreImagen = nombreImagen;
        this.imagen = imagen;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
