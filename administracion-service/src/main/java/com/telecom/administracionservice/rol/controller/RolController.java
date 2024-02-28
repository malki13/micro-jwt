package com.telecom.administracionservice.rol.controller;

import com.telecom.administracionservice.util.response.Response;
import com.telecom.administracionservice.rol.data.dto.RolDTO;
import com.telecom.administracionservice.rol.data.read.RolRead;
import com.telecom.administracionservice.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("/")
    public ResponseEntity<Page<RolRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(rolService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{rolId}")
    public ResponseEntity<Response> getOne(
            @PathVariable("rolId") Integer rolId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol encontrado", rolService.getOne(rolId).get()), HttpStatus.OK);
    }

    @PostMapping("/{estatusId}")
    public ResponseEntity<Response> save(
            @PathVariable("estatusId") Integer estatusId,
            @RequestBody RolDTO rolDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Rol creado", rolService.save(estatusId, rolDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{rolId}")
    public ResponseEntity<Response> delete(
            @PathVariable("rolId") Integer rolId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol eliminado", rolService.delete(rolId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}/{rolId}")
    public ResponseEntity<Response> update(
            @PathVariable("estatusId") Integer estatusId,
            @PathVariable("rolId") Integer rolId,
            @RequestBody RolDTO rolDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol actualizado", rolService.update(estatusId, rolId, rolDTO)), HttpStatus.OK);
    }

}
