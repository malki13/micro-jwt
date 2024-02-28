package com.telecom.administracionservice.usuario.data.info;

import com.telecom.administracionservice.estatus.data.dto.EstatusDTO;
import com.telecom.administracionservice.interventor.data.info.InterventorInfo;

public class UsuarioInfo {
    private Integer iden;
    private String nombre;
    private EstatusDTO estatus;
    private InterventorInfo interventor;

    public UsuarioInfo() {
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

    public EstatusDTO getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusDTO estatus) {
        this.estatus = estatus;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }
}
