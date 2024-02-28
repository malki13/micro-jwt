package com.telecom.administracionservice.rol.data.read;

import com.telecom.administracionservice.estatus.data.read.EstatusRead;

public class RolRead {
    private Integer iden;
    private String nombre;
    private String descripcion;
    private EstatusRead estatus;

    public RolRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstatusRead getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusRead estatus) {
        this.estatus = estatus;
    }
}
