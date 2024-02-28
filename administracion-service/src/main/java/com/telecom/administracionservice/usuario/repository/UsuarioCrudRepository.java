package com.telecom.administracionservice.usuario.repository;

import com.telecom.administracionservice.usuario.data.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioCrudRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByNombre(String nombre);
}
