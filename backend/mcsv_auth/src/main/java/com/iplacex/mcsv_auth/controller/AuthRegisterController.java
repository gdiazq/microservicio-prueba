package com.iplacex.mcsv_auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iplacex.mcsv_auth.dto.AuthRegisterRequestDto;
import com.iplacex.mcsv_auth.dto.AuthRegisterResponseDto;
import com.iplacex.mcsv_auth.dto.mapper.AuthUserRegisterMapper;
import com.iplacex.mcsv_auth.service.AuthRegisterServiceInter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRegisterController {

    private final AuthRegisterServiceInter userRegistrationService;

    private final AuthUserRegisterMapper userRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponseDto> registerUser(
      @Validated @RequestBody final AuthRegisterRequestDto registrationDTO) {

        final var registeredUser = userRegistrationService
          .registerUser(userRegistrationMapper.toEntity(registrationDTO));

        return ResponseEntity.ok(
          userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }

}
