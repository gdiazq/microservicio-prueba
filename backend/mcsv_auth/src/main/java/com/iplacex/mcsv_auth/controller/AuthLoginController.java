package com.iplacex.mcsv_auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iplacex.mcsv_auth.dto.AuthLoginRequestDto;
import com.iplacex.mcsv_auth.dto.AuthLoginResponseDto;
import com.iplacex.mcsv_auth.service.AuthLoginServiceInter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthLoginController {
    
        private final AuthLoginServiceInter userLoginService;
    
        @PostMapping("/login")
        public ResponseEntity<AuthLoginResponseDto> autenticate(
            @RequestBody final AuthLoginRequestDto authLoginRequestDto) {
    
                return ResponseEntity.ok(userLoginService.authenticate(authLoginRequestDto));
            }
}
