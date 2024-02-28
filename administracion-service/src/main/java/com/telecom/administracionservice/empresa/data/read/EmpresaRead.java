package com.telecom.administracionservice.empresa.data.read;

import com.telecom.administracionservice.interventor.data.info.InterventorInfo;

public class EmpresaRead {
    private Integer iden;
    private Integer numGateways;
    private Integer numDevices;
    private InterventorInfo interventor;

    public EmpresaRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public Integer getNumGateways() {
        return numGateways;
    }

    public void setNumGateways(Integer numGateways) {
        this.numGateways = numGateways;
    }

    public Integer getNumDevices() {
        return numDevices;
    }

    public void setNumDevices(Integer numDevices) {
        this.numDevices = numDevices;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }
}
