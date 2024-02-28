package com.telecom.administracionservice.rol.repository;

import com.telecom.administracionservice.rol.data.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolCrudRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByNombre(String nombre);
}
