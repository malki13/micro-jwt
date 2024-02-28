package com.telecom.administracionservice.usuario.controller;

import com.telecom.administracionservice.util.response.Response;
import com.telecom.administracionservice.usuario.data.dto.UsuarioDTO;
import com.telecom.administracionservice.usuario.data.info.UsuarioInfo;
import com.telecom.administracionservice.usuario.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public ResponseEntity<Page<UsuarioInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(usuarioService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Response> getOne(
            @PathVariable("usuarioId") Integer usuarioId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario encontrado", usuarioService.getOne(usuarioId).get()), HttpStatus.OK);
    }

    @GetMapping("/getByEmail/")
    public ResponseEntity<Response> getByEmail(
            @RequestParam String email
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario encontrado", usuarioService.getByNombre(email).get()), HttpStatus.OK);
    }

    @PostMapping("/save/{estatusId}/{interventorId}/{empresaId}")
    public ResponseEntity<Response> save(
            @PathVariable("estatusId") Integer estatusId,
            @PathVariable("interventorId") Integer interventorId,
            @PathVariable("empresaId") Integer empresaId,
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Usuario creado", usuarioService.save(estatusId, interventorId, empresaId, usuarioDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Response> delete(
            @PathVariable("usuarioId") Integer usuarioId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario eliminado", usuarioService.delete(usuarioId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}/{interventorId}/{empresaId}/{usuarioId}")
    public ResponseEntity<Response> update(
            @PathVariable("estatusId") Integer estatusId,
            @PathVariable("interventorId") Integer interventorId,
            @PathVariable("empresaId") Integer empresaId,
            @PathVariable("usuarioId") Integer usuarioId,
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario actualizado", usuarioService.update(estatusId, interventorId, empresaId, usuarioId, usuarioDTO)), HttpStatus.OK);
    }
    @PostMapping("/{usuarioId}/{rolId}")
    public ResponseEntity<Response> addRol(
            @PathVariable("usuarioId") Integer usuarioId,
            @PathVariable("rolId") Integer rolId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol agregado", usuarioService.addRol(usuarioId, rolId)), HttpStatus.OK);
    }

}
