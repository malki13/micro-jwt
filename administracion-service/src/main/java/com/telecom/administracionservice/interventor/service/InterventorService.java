package com.telecom.administracionservice.interventor.service;

import com.telecom.administracionservice.interventor.data.dto.InterventorDTO;
import com.telecom.administracionservice.interventor.data.info.InterventorInfo;
import com.telecom.administracionservice.interventor.data.read.InterventorRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InterventorService {
    Page<InterventorInfo> findAll(Pageable pageable);

    Optional<InterventorInfo> getOne(Integer id);

    InterventorRead save(Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception;

    boolean delete(Integer id);

    InterventorInfo update(Integer interventorId, Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception;

    List<InterventorInfo> searchByCodigo(String codigo);

    List<InterventorInfo> searchByApellido(String apellido);

    Optional<InterventorInfo> findByCodigo(String codigo);
}
