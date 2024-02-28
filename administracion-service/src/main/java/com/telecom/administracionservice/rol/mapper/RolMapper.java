package com.telecom.administracionservice.rol.mapper;

import com.telecom.administracionservice.estatus.mapper.EstatusMapper;
import com.telecom.administracionservice.rol.data.dto.RolDTO;
import com.telecom.administracionservice.rol.data.entity.Rol;
import com.telecom.administracionservice.rol.data.read.RolRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {EstatusMapper.class})
public interface RolMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "estatus", target = "estatus"),
    })
    RolRead toRolRead(Rol rol);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "estatus", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Rol toRol(RolDTO rolDTO);

}
