package com.telecom.administracionservice.estatus.mapper;

import com.telecom.administracionservice.estatus.data.dto.EstatusDTO;
import com.telecom.administracionservice.estatus.data.entity.Estatus;
import com.telecom.administracionservice.estatus.data.read.EstatusRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface EstatusMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    EstatusRead toEstatusRead(Estatus estatus);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Estatus toEstatus(EstatusDTO estatusDTO);
}
