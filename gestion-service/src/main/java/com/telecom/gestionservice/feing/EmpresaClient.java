package com.telecom.gestionservice.feing;

import com.telecom.gestionservice.util.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "administracion-service",
//        url = "http://localhost:8091",
//        url = "http://backendgestion:8091",
        path = "/telecom-cedia/api/administracion/empresa")
public interface EmpresaClient {
    @GetMapping("/{empresaId}")
    ResponseEntity<Response> getOne(@PathVariable("empresaId") Integer empresaId);
}
