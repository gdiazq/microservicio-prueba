package com.iplacex.mcsv_auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.iplacex.mcsv_auth.dto.AuthLoginRequestDto;
import com.iplacex.mcsv_auth.dto.AuthLoginResponseDto;
import com.iplacex.mcsv_auth.jwt.JwtService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AuthLoginServiceImpl implements AuthLoginServiceInter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthLoginResponseDto authenticate(final AuthLoginRequestDto request) {

        final var authToken = UsernamePasswordAuthenticationToken
            .unauthenticated(request.getUsername(), request.getPassword());

        authenticationManager.authenticate(authToken);

        final var token = jwtService.generateToken(request.getUsername());

        return new AuthLoginResponseDto(token);
    }

}
