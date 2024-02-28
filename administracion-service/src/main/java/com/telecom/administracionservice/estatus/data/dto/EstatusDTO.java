package com.telecom.administracionservice.estatus.data.dto;

public class EstatusDTO {
    private String codigo;
    private String nombre;

    public EstatusDTO() {
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
}
