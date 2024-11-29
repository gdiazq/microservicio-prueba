package com.iplacex.mcsv_auth.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iplacex.mcsv_auth.model.AuthEntity;
import com.iplacex.mcsv_auth.repository.AuthRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AuthRegisterServiceImpl implements AuthRegisterServiceInter {
    
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public AuthEntity registerUser(AuthEntity authEntity) {
        if (authRepository.existsByUsername(authEntity.getUsername()) || authRepository.existsByEmail(authEntity.getEmail())) {

          throw new IllegalArgumentException(
            "Username or Email already exists");
        }

        AuthEntity authUser = new AuthEntity();
        authUser.setUsername(authEntity.getUsername());
        authUser.setEmail(authEntity.getEmail());
        authUser.setPassword(passwordEncoder.encode(authEntity.getPassword()));

        return authRepository.save(authUser);
    }

}
    
