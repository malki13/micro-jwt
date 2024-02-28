package com.telecom.administracionservice.empresa.service;

import com.telecom.administracionservice.empresa.data.dto.EmpresaDTO;
import com.telecom.administracionservice.empresa.data.entity.Empresa;
import com.telecom.administracionservice.empresa.data.read.EmpresaRead;
import com.telecom.administracionservice.empresa.mapper.EmpresaMapper;
import com.telecom.administracionservice.empresa.repository.EmpresaCrudRepository;
import com.telecom.administracionservice.interventor.data.entity.Interventor;
import com.telecom.administracionservice.interventor.repository.InterventorCrudRepository;
import com.telecom.administracionservice.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService{
    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;
    @Autowired
    private EmpresaMapper empresaMapper;
    @Autowired
    private InterventorCrudRepository interventorCrudRepository;
    @Override
    public Page<EmpresaRead> findAll(Pageable pageable) {
        return empresaCrudRepository.findAll(pageable).map(empresa -> empresaMapper.toEmpresaRead(empresa));
    }

    @Override
    public Optional<EmpresaRead> getOne(Integer empresaId) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaId == 0) {
            throw new BadRequestException("El id de la empresa debe ser diferente 0");
        }
        if (empresaDB.isPresent()) {
            return empresaDB.map(empresa -> empresaMapper.toEmpresaRead(empresa));
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrada");
    }

    @Override
    public EmpresaRead save(Integer interventorId, EmpresaDTO empresaDTO) {
        Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
        if (interventorDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findByInterventorIden(interventorId);
            if (!empresaDB.isPresent()) {
                Empresa empresa = empresaMapper.toEmpresa(empresaDTO);
                empresa.setInterventor(interventorDB.get());
                return empresaMapper.toEmpresaRead(empresaCrudRepository.save(empresa));
            }
            throw new BadRequestException("Empresa con interventor " + interventorDB.get().getCodigo() + " ya esta registrada");
        }
        throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar a la empresa");
    }

    @Override
    public boolean delete(Integer empresaId) {
        try {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                empresaCrudRepository.deleteById(empresaId);
                return true;
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para eliminar");
        } catch (Exception exception){
            throw new BadRequestException("La empresa que intenta eliminar cuenta con gateways, usuarios, clientes, etc. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la empresa no debe tener gateways, usuarios, clientes, etc. Relacionados con esta empresa.");
        }
    }

    @Override
    public EmpresaRead update(Integer interventorId, Integer empresaId, EmpresaDTO empresaDTO) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Empresa empresa = empresaDB.get();
                Empresa newEmpresa = empresaMapper.toEmpresa(empresaDTO);
                empresa.setNumDevices(newEmpresa.getNumDevices());
                empresa.setNumGateways(newEmpresa.getNumGateways());
                empresa.setUpdatedAt(LocalDateTime.now());
                empresa.setInterventor(interventorDB.get());
                return empresaMapper.toEmpresaRead(empresaCrudRepository.save(empresa));
            }
            throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para actualizar");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para actualizar");
    }
}
