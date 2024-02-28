package com.telecom.administracionservice.interventor.service;

import com.telecom.administracionservice.interventor.data.dto.InterventorDTO;
import com.telecom.administracionservice.interventor.data.entity.Interventor;
import com.telecom.administracionservice.interventor.data.info.InterventorInfo;
import com.telecom.administracionservice.interventor.data.read.InterventorRead;
import com.telecom.administracionservice.interventor.mapper.InterventorMapper;
import com.telecom.administracionservice.interventor.repository.InterventorCrudRepository;
import com.telecom.administracionservice.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IntervertorServiceImpl implements InterventorService{
    @Autowired
    private InterventorCrudRepository interventorCrudRepository;
    @Autowired
    private InterventorMapper interventorMapper;
    @Autowired
    private Environment env;

    String rootPath = "/home/uploads";
    @Override
    public Page<InterventorInfo> findAll(Pageable pageable) {
        return interventorCrudRepository.findAll(pageable).map(interventor -> interventorMapper.toInterventorInfo(interventor));
    }

    @Override
    public Optional<InterventorInfo> getOne(Integer id) {
        Optional<Interventor> interventorDB = interventorCrudRepository.findById(id);
        if (interventorDB.isPresent()) {
            return interventorDB.map(interventor -> interventorMapper.toInterventorInfo(interventor));
        } else {
            throw new BadRequestException("Interventor con id " + id + " no encontrado");
        }
    }

    @Override
    public InterventorRead save(Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception {
        Optional<Interventor> interventoDB = interventorCrudRepository.findByCodigo(interventorDTO.getCodigo());
        if (!interventoDB.isPresent()) {
            if (interventorDTO.getBase64() != null) {
                if (!interventorDTO.getBase64().isEmpty()) {
                    String[] base64 = interventorDTO.getBase64().split(",");
                    String extension;
                    switch (base64[0]) {
                        case "data:image/jpeg;base64":
                            extension = ".jpeg";
                            break;
                        case "data:image/png;base64":
                            extension = ".png";
                            break;
                        default:
                            extension = ".jpg";
                            break;
                    }
                    String uniqueFilename = UUID.randomUUID().toString() + extension;
                    String rutaCompleta = rootPath + "/" + uniqueFilename;
                    byte[] bytes = DatatypeConverter.parseBase64Binary(base64[1]);
                    File outputfile = new File(rutaCompleta);
                    try {
                        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                        outputStream.write(bytes);
                        outputStream.close();
                    } catch (Exception exception) {
                        throw new Exception(exception.getMessage());
                    }
                    String url = getUrlRemota() + "/" + uniqueFilename;
                    interventorDTO.setNombreImagen(uniqueFilename);
                    interventorDTO.setImagen(url);
                }
            }
            Interventor interventor = interventorMapper.toInterventor(interventorDTO);
//            interventor.setInterventorTipo(interventorTipo.get());
            //interventor.setFechaNacimiento(sumarDiasFecha(interventor.getFechaNacimiento(), 1));
            //System.out.println("FECHA DE NACIMIENTO ANTES DE GUARDAR " + interventor.getFechaNacimiento());
            return interventorMapper.toInterventorRead(interventorCrudRepository.save(interventor));
        }
        throw new BadRequestException("Interventor con codigo " + interventoDB.get().getCodigo() + " ya esta registrado");
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(id);
            if (interventorDB.isPresent()) {
                String rootPath = "/home/uploads";
                Path rutaCompleta = Paths.get(rootPath + "/" + interventorDB.get().getNombreImagen());
                File image = rutaCompleta.toFile();
                if (image.exists()) image.delete();
                interventorCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Interventor con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El interventor que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el interventor no debe contar con objetos relacionados al mismo.");
        }
    }

    @Override
    public InterventorInfo update(Integer interventorId, Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception {
        return null;
    }

    @Override
    public List<InterventorInfo> searchByCodigo(String codigo) {
        return null;
    }

    @Override
    public List<InterventorInfo> searchByApellido(String apellido) {
        return null;
    }

    @Override
    public Optional<InterventorInfo> findByCodigo(String codigo) {
        return Optional.empty();
    }
    public String getUrlRemota() {
        String puerto = env.getProperty("server.port");
        String servidor = env.getProperty("remoto");
        String context = env.getProperty("server.servlet.context-path");
        return "http://" + servidor + ":" + puerto + context + "/uploads";
    }
}
