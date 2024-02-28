package com.telecom.administracionservice.interventor.mapper;

import com.telecom.administracionservice.interventor.data.dto.InterventorDTO;
import com.telecom.administracionservice.interventor.data.entity.Interventor;
import com.telecom.administracionservice.interventor.data.info.InterventorInfo;
import com.telecom.administracionservice.interventor.data.read.InterventorRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InterventorMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
//            @Mapping(source = "interventorTipo", target = "interventorTipo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "referencia", target = "referencia"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "nombreImagen", target = "nombreImagen"),
            @Mapping(source = "imagen", target = "imagen")
    })
    InterventorRead toInterventorRead(Interventor interventor);
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
//            @Mapping(source = "interventorTipo", target = "interventorTipo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "referencia", target = "referencia"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "imagen", target = "imagen"),
    })
    InterventorInfo toInterventorInfo(Interventor interventor);
    @InheritInverseConfiguration
    @Mappings({
//            @Mapping(target = "interventorTipo", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            //@Mapping(target = "empresa", ignore = true),
            //@Mapping(target = "usuario", ignore = true),
    })
    Interventor toInterventor(InterventorDTO interventorDTO);

}
