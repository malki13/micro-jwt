package com.telecom.administracionservice.empresa.data.dto;

public class EmpresaDTO {
    private Integer numGateways;
    private Integer numDevices;

    public EmpresaDTO() {
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
}
