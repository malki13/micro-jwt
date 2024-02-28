package com.telecom.administracionservice.estatus.controller;

import com.telecom.administracionservice.estatus.data.dto.EstatusDTO;
import com.telecom.administracionservice.estatus.data.read.EstatusRead;
import com.telecom.administracionservice.estatus.service.EstatusService;
import com.telecom.administracionservice.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatus")
public class EstatusControlller {
    @Autowired
    private EstatusService estatusService;

    @GetMapping("/")
    public ResponseEntity<Page<EstatusRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(estatusService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{estatusId}")
    public ResponseEntity<Response> getOne(
            @PathVariable("estatusId") Integer estatusId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus encontrado", estatusService.getOne(estatusId).get()), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Response> save(
            @RequestBody EstatusDTO estatusDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Estatus creado", estatusService.save(estatusDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{estatusId}")
    public ResponseEntity<Response> delete(
            @PathVariable("estatusId") Integer estatusId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus eliminado", estatusService.delete(estatusId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}")
    public ResponseEntity<Response> update(
            @RequestBody EstatusDTO estatusDTO,
            @PathVariable("id") Integer estatusId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus actualizado", estatusService.update(estatusDTO, estatusId)), HttpStatus.OK);
    }
}
