package com.telecom.authservice.repository;

import com.telecom.authservice.data.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
}
