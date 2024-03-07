package com.telecom.gestionservice.gateway.controller;

import com.telecom.gestionservice.gateway.data.dto.GatewayDTO;
import com.telecom.gestionservice.gateway.data.info.GatewayInfo;
import com.telecom.gestionservice.gateway.service.GatewayService;
import com.telecom.gestionservice.response.Response;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/gestion")
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
//        Bike bike = bikeService.getBikeById(id);
//        if(bike == null)
//            return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Hola"+String.valueOf(id));
    }

    @GetMapping("/getOne/{gatewayId}")
    /*@ApiOperation(value = "Obtiene un Gateway por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Gateway no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })*/
    public ResponseEntity<Response> getOne(
            /*@ApiParam(value = "ID del gateway", required = true)*/ @PathVariable("gatewayId") Integer gatewayId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Gateway encontrado", gatewayService.getOne(gatewayId).get()), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Page<GatewayInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(gatewayService.findAll(pageable), HttpStatus.OK);
    }
    @PostMapping("/{empresaId}")
    /*@ApiOperation(value = "Guarda un Gateway según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Gateway creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Gateway"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })*/
    public ResponseEntity<Response> save(
            /*@ApiParam(value = "ID de la empresa", required = true)*/ @PathVariable("empresaId") Integer empresaId,
            /*@Valid @ApiParam(value = "Información del gateway", required = true)*/ @RequestBody GatewayDTO gatewayDTO
    ) throws BadRequestException {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Gateway creado", gatewayService.save(empresaId, gatewayDTO)), HttpStatus.CREATED);
    }
}
