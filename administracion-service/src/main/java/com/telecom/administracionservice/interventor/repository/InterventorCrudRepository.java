package com.telecom.administracionservice.interventor.repository;

import com.telecom.administracionservice.interventor.data.entity.Interventor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterventorCrudRepository extends JpaRepository<Interventor,Integer> {
    Optional<Interventor> findByCodigo(String codigo);

    Optional<Interventor> findByEmail(String email);
}
