package com.telecom.administracionservice.estatus.service;

import com.telecom.administracionservice.estatus.data.dto.EstatusDTO;
import com.telecom.administracionservice.estatus.data.entity.Estatus;
import com.telecom.administracionservice.estatus.data.read.EstatusRead;
import com.telecom.administracionservice.estatus.mapper.EstatusMapper;
import com.telecom.administracionservice.estatus.repository.EstatusCrudRepository;
import com.telecom.administracionservice.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EstatusServiceImpl implements EstatusService{
    @Autowired
    private EstatusCrudRepository estatusCrudRepository;
    @Autowired
    private EstatusMapper estatusMapper;
    @Override
    public Page<EstatusRead> findAll(Pageable pageable) {
        return estatusCrudRepository.findAll(pageable).map(estatus -> estatusMapper.toEstatusRead(estatus));
    }

    @Override
    public Optional<EstatusRead> getOne(Integer id) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
        if (estatusDB.isPresent()) {
            return estatusDB.map(estatus -> estatusMapper.toEstatusRead(estatus));
        }
        throw new BadRequestException("Estatus con id " + id + " no encontrado");
    }

    @Override
    public EstatusRead save(EstatusDTO estatusDTO) {
        Optional<Estatus> estatusDBCodigo = estatusCrudRepository.findByCodigo(estatusDTO.getCodigo());
        if (!estatusDBCodigo.isPresent()) {
            Optional<Estatus> estatusDBNombre = estatusCrudRepository.findByNombre(estatusDTO.getNombre());
            if (!estatusDBNombre.isPresent()) {
                Estatus estatus = estatusMapper.toEstatus(estatusDTO);
                return estatusMapper.toEstatusRead(estatusCrudRepository.save(estatus));
            }
            throw new BadRequestException("Estatus con nombre " + estatusDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("Estatus con codigo " + estatusDTO.getCodigo() + " ya esta registrado");
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
            if (estatusDB.isPresent()) {
                estatusCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Estatus con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El estatus que intenta eliminar cuenta con objetos relacionados al mismo. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el estatus no debe contar con objetos relacionados.");
        }
    }

    @Override
    public EstatusRead update(EstatusDTO estatusDTO, Integer id) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
        if (estatusDB.isPresent()) {
            Estatus newEstatus = estatusMapper.toEstatus(estatusDTO);
            Estatus est = estatusDB.get();
            est.setCodigo(newEstatus.getCodigo());
            est.setNombre(newEstatus.getNombre());
            est.setUpdatedAt(LocalDateTime.now());
            return estatusMapper.toEstatusRead(estatusCrudRepository.save(est));
        }
        throw new BadRequestException("Estatus con id " + id + " no encontrado para actualizar");
    }
}
