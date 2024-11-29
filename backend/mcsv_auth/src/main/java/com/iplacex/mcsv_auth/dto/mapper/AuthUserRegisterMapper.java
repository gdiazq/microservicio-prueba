package com.iplacex.mcsv_auth.dto.mapper;

import org.springframework.stereotype.Component;

import com.iplacex.mcsv_auth.dto.AuthRegisterRequestDto;
import com.iplacex.mcsv_auth.dto.AuthRegisterResponseDto;
import com.iplacex.mcsv_auth.model.AuthEntity;

@Component
public class AuthUserRegisterMapper {

    public AuthEntity toEntity(AuthRegisterRequestDto authRegisterRequestDto) {
        final var user = new AuthEntity();

        user.setEmail(authRegisterRequestDto.getEmail());
        user.setUsername(authRegisterRequestDto.getUsername());
        user.setPassword(authRegisterRequestDto.getPassword());

        return user;
    }

    public AuthRegisterResponseDto toRegistrationResponseDto(
      final AuthEntity user) {
        
        return new AuthRegisterResponseDto(
          user.getEmail(), 
          user.getUsername()
        );
      }

}
