package com.telecom.administracionservice.rol.service;

import com.telecom.administracionservice.estatus.data.entity.Estatus;
import com.telecom.administracionservice.estatus.repository.EstatusCrudRepository;
import com.telecom.administracionservice.util.response.error.BadRequestException;
import com.telecom.administracionservice.rol.data.dto.RolDTO;
import com.telecom.administracionservice.rol.data.entity.Rol;
import com.telecom.administracionservice.rol.data.read.RolRead;
import com.telecom.administracionservice.rol.mapper.RolMapper;
import com.telecom.administracionservice.rol.repository.RolCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolCrudRepository rolCrudRepository;
    @Autowired
    private RolMapper rolMapper;
    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Override
    public Page<RolRead> findAll(Pageable pageable) {
        return rolCrudRepository.findAll(pageable).map(rol -> rolMapper.toRolRead(rol));
    }

    @Override
    public Optional<RolRead> getOne(Integer id) {
        Optional<Rol> rolDB = rolCrudRepository.findById(id);
        if (rolDB.isPresent()) {
            return rolDB.map(rol -> rolMapper.toRolRead(rol));
        }
        throw new BadRequestException("Rol con id " + id + " no encontrado");
    }

    @Override
    public RolRead save(Integer estatusId, RolDTO rolDTO) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Rol> rolDB = rolCrudRepository.findByNombre(rolDTO.getNombre());
            if (!rolDB.isPresent()) {
                Rol rol = rolMapper.toRol(rolDTO);
                rol.setEstatus(estatusDB.get());
                return rolMapper.toRolRead(rolCrudRepository.save(rol));
            }
            throw new BadRequestException("Rol con nombre  " + rolDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar a el rol");
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Rol> rolDB = rolCrudRepository.findById(id);
            if (rolDB.isPresent()) {
                rolCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Rol con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El rol que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el rol no debe contar con objetos relacionados al mismo.");
        }
    }

    @Override
    public RolRead update(Integer estatusId, Integer id, RolDTO rolDTO) {
        Optional<Rol> rolDB = rolCrudRepository.findById(id);
        if (rolDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Rol rol = rolDB.get();
                Rol newRol = rolMapper.toRol(rolDTO);
                rol.setNombre(newRol.getNombre());
                rol.setDescripcion(newRol.getDescripcion());
                rol.setEstatus(estatusDB.get());
                rol.setUpdatedAt(LocalDateTime.now());
                return rolMapper.toRolRead(rolCrudRepository.save(rol));
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para actualizar el rol");
        }
        throw new BadRequestException("Rol con id " + id + " no encontrado para actualizar");
    }
}
