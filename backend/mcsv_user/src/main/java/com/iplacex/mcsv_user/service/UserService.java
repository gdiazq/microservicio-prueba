package com.iplacex.mcsv_user.service;

import java.util.List;
import java.util.Optional;

import com.iplacex.mcsv_user.dto.UserDto;
import com.iplacex.mcsv_user.model.UserEntity;

public interface UserService {

    Iterable <UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    List<UserDto> findAllWithZone();

    UserEntity save(UserEntity userEntity);

}
