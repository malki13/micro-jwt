package com.telecom.administracionservice.rol.service;

import com.telecom.administracionservice.rol.data.dto.RolDTO;
import com.telecom.administracionservice.rol.data.read.RolRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RolService {
    Page<RolRead> findAll(Pageable pageable);

    Optional<RolRead> getOne(Integer id);

    RolRead save(Integer estatusId, RolDTO rolDTO);

    boolean delete(Integer id);

    RolRead update(Integer estatusId, Integer id, RolDTO rolDTO);
}
