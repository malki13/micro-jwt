package com.telecom.administracionservice.empresa.controller;

import com.telecom.administracionservice.empresa.data.dto.EmpresaDTO;
import com.telecom.administracionservice.empresa.data.read.EmpresaRead;
import com.telecom.administracionservice.empresa.service.EmpresaService;
import com.telecom.administracionservice.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/")
    public ResponseEntity<Page<EmpresaRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(empresaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<Response> getOne(
            @PathVariable("empresaId") Integer empresaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Empresa encontrada", empresaService.getOne(empresaId).get()), HttpStatus.OK);
    }

    @PostMapping("/{interventorId}")
    public ResponseEntity<Response> save(
            @PathVariable("interventorId") Integer interventorId,
            @RequestBody EmpresaDTO empresaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Empresa creada", empresaService.save(interventorId, empresaDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{interventorId}")
    public ResponseEntity<Response> delete(
            @PathVariable("interventorId") Integer interventorId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Empresa eliminada", empresaService.delete(interventorId)), HttpStatus.OK);
    }

    @PutMapping("/{interventorId}/{empresaId}")
    public ResponseEntity<Response> update(
            @PathVariable("interventorId") Integer interventorId,
            @PathVariable("empresaId") Integer empresaId,
            @RequestBody EmpresaDTO empresaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Empresa actualizada", empresaService.update(interventorId, empresaId, empresaDTO)), HttpStatus.OK);
    }

}
