package com.telecom.administracionservice.usuario.service;

import com.telecom.administracionservice.usuario.data.dto.UsuarioDTO;
import com.telecom.administracionservice.usuario.data.info.UsuarioInfo;
import com.telecom.administracionservice.usuario.data.read.UsuarioRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {
    Page<UsuarioInfo> findAll(Pageable pageable);

    Optional<UsuarioRead> getOne(Integer id);

    Optional<UsuarioRead> getByNombre(String nombre);

    UsuarioInfo save(Integer estatusId, Integer interventorId, Integer empresaId, UsuarioDTO usuarioDTO);

    boolean delete(Integer id);

    UsuarioInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id, UsuarioDTO usuarioDTO);
}
