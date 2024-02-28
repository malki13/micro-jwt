package com.telecom.administracionservice.estatus.repository;

import com.telecom.administracionservice.estatus.data.entity.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstatusCrudRepository extends JpaRepository<Estatus,Integer> {
    Optional<Estatus> findByCodigo(String codigo);
    Optional<Estatus> findByNombre(String nombre);

}
