package com.iplacex.mcsv_user.service;

import java.util.Optional;

import com.iplacex.mcsv_user.model.UserEntity;

public interface UserService {

    Iterable <UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity userEntity);

}
