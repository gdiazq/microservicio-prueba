package com.iplacex.mcsv_auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iplacex.mcsv_auth.model.AuthEntity;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {

    Optional<AuthEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
}
