package com.iplacex.mcsv_auth.dto.mapper;

import org.springframework.stereotype.Component;

import com.iplacex.mcsv_auth.dto.UserProfileDto;
import com.iplacex.mcsv_auth.model.AuthEntity;

@Component
public class UserMapper {

    public UserProfileDto toUserProfileDto(final AuthEntity authEntity) {
        return new UserProfileDto(authEntity.getEmail(), authEntity.getUsername());
    }

}
