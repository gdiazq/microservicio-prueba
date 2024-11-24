package com.iplacex.mcsv_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iplacex.mcsv_user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
