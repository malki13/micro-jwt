package com.telecom.administracionservice.interventor.controller;

import com.telecom.administracionservice.interventor.data.dto.InterventorDTO;
import com.telecom.administracionservice.interventor.data.info.InterventorInfo;
import com.telecom.administracionservice.interventor.service.InterventorService;
import com.telecom.administracionservice.util.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin
@RestController
@RequestMapping("/interventor")
@Tag(name = "Interventor",description = "REST API relacionada a la entidad de Interventor")
public class InterventorController {
    @Autowired
    private InterventorService interventorService;
//    @CrossOrigin
    @GetMapping("/saludo/{id}")
//    @Operation(security = {@SecurityScheme()
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok("Hola"+String.valueOf(id));
    }
    @GetMapping("/")
    public ResponseEntity<Page<InterventorInfo>> findAll(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(interventorService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{interventorId}")
    public ResponseEntity<Response> getOne(
            @PathVariable("interventorId") Integer interventorId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Interventor encontrado", interventorService.getOne(interventorId).get()), HttpStatus.OK);
    }

    @PostMapping(value = "/{interventorTipoId}")
    public ResponseEntity<Response> save(
            @PathVariable("interventorTipoId") Integer interventorTipoId,
            @RequestBody InterventorDTO interventorDTO
    ) throws Exception {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Interventor creado", interventorService.save(interventorTipoId, interventorDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{interventorId}")
    public ResponseEntity<Response> delete(
            @PathVariable("interventorId") Integer interventorId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Interventor eliminado", interventorService.delete(interventorId)), HttpStatus.OK);
    }

}
