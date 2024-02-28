package com.telecom.administracionservice.usuario.mapper;

import com.telecom.administracionservice.empresa.mapper.EmpresaMapper;
import com.telecom.administracionservice.estatus.mapper.EstatusMapper;
import com.telecom.administracionservice.interventor.mapper.InterventorMapper;
import com.telecom.administracionservice.rol.mapper.RolMapper;
import com.telecom.administracionservice.usuario.data.dto.UsuarioDTO;
import com.telecom.administracionservice.usuario.data.entity.Usuario;
import com.telecom.administracionservice.usuario.data.info.UsuarioInfo;
import com.telecom.administracionservice.usuario.data.read.UsuarioRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {EstatusMapper.class, InterventorMapper.class, RolMapper.class, EmpresaMapper.class})
public interface UsuarioMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "rols", target = "roles"),
            @Mapping(source = "empresa", target = "empresa"),
    })
    UsuarioRead toUsuarioRead(Usuario usuario);
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "interventor", target = "interventor"),
    })
    UsuarioInfo toUsuarioInfo(Usuario usuario);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "roles", target = "rols"),
            @Mapping(target = "estatus", ignore = true),
            @Mapping(target = "interventor", ignore = true),
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Usuario toUsuario(UsuarioDTO usuarioDTO);

}
