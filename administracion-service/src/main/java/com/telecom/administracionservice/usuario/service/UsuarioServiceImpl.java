package com.telecom.administracionservice.usuario.service;

import com.telecom.administracionservice.empresa.data.entity.Empresa;
import com.telecom.administracionservice.empresa.repository.EmpresaCrudRepository;
import com.telecom.administracionservice.estatus.data.entity.Estatus;
import com.telecom.administracionservice.estatus.repository.EstatusCrudRepository;
import com.telecom.administracionservice.interventor.data.entity.Interventor;
import com.telecom.administracionservice.interventor.repository.InterventorCrudRepository;
import com.telecom.administracionservice.util.response.error.BadRequestException;
import com.telecom.administracionservice.rol.data.entity.Rol;
import com.telecom.administracionservice.rol.repository.RolCrudRepository;
import com.telecom.administracionservice.usuario.data.dto.UsuarioDTO;
import com.telecom.administracionservice.usuario.data.entity.Usuario;
import com.telecom.administracionservice.usuario.data.info.UsuarioInfo;
import com.telecom.administracionservice.usuario.data.read.UsuarioRead;
import com.telecom.administracionservice.usuario.mapper.UsuarioMapper;
import com.telecom.administracionservice.usuario.repository.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private InterventorCrudRepository interventorCrudRepository;
    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;
    @Autowired
    private EstatusCrudRepository estatusCrudRepository;
    @Autowired
    private RolCrudRepository rolCrudRepository;


    @Override
    public Page<UsuarioInfo> findAll(Pageable pageable) {
        return usuarioCrudRepository.findAll(pageable).map(usuario -> usuarioMapper.toUsuarioInfo(usuario));
    }

    @Override
    public Optional<UsuarioRead> getOne(Integer id) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
        if (usuarioDB.isPresent()) {
            return usuarioDB.map(usuario -> usuarioMapper.toUsuarioRead(usuario));
        }
        throw new BadRequestException("Usuario con id " + id + " no encontrado");
    }

    @Override
    public Optional<UsuarioRead> getByNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public UsuarioInfo save(Integer estatusId, Integer interventorId, Integer empresaId, UsuarioDTO usuarioDTO) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                if (empresaDB.isPresent()) {
                    Optional<Usuario> usuarioDB = usuarioCrudRepository.findByNombre(usuarioDTO.getNombre());
                    if (!usuarioDB.isPresent()) {
                        Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
                        usuario.setEstatus(estatusDB.get());
                        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
                        usuario.setInterventor(interventorDB.get());
                        usuario.setEmpresa(empresaDB.get());
                        return usuarioMapper.toUsuarioInfo(usuarioCrudRepository.save(usuario));
                    }
                    throw new BadRequestException("Usuario con nombre " + usuarioDTO.getNombre() + " ya esta registrado");
                }
                throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar al usuario");
            }
            throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar al usuario");
        }
        throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar al usuario");
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
            if (usuarioDB.isPresent()) {
                usuarioCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Usuario con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El usuario que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el usuario no debe contar con objetos relacionados al mismo.");
        }
    }

    @Override
    public UsuarioInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
        if (usuarioDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                if (interventorDB.isPresent()) {
                    Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                    if (empresaDB.isPresent()) {
                        Usuario usuario = usuarioDB.get();
                        Usuario newUsuario = usuarioMapper.toUsuario(usuarioDTO);
                        usuario.setNombre(newUsuario.getNombre());
                        usuario.setEstatus(estatusDB.get());
                        usuario.setInterventor(interventorDB.get());
                        usuario.setEmpresa(empresaDB.get());
                        usuario.setRols(newUsuario.getRols());
                        usuario.setUpdatedAt(LocalDateTime.now());
                        return usuarioMapper.toUsuarioInfo(usuarioCrudRepository.save(usuario));
                    }
                    throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar al usuario");
                }
                throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar al usuario");
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar al usuario");
        }
        throw new BadRequestException("Usuario con id " + id + " no encontrado para actualizar");
    }
    public boolean addRol(Integer usuarioId, Integer rolId) {
        Optional<Rol> rolDB = rolCrudRepository.findById(rolId);
        if (rolDB.isPresent()) {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
            if (usuarioDB.isPresent()) {
                Usuario usuario = usuarioDB.get();
                usuario.addRol(rolDB.get());
                usuarioCrudRepository.save(usuario);
                return true;
            }
            throw new BadRequestException("Usuario con id " + usuarioId + " no encontrado para relacionar el rol");
        }
        throw new BadRequestException("Rol con id " + rolId + " no encontrado para relacionar a usuario");
    }
}
