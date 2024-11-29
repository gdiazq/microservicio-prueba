package com.iplacex.mcsv_auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iplacex.mcsv_auth.repository.AuthRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) 
      throws UsernameNotFoundException {

        return authRepository.findByUsername(username).map(user ->
                User.builder()
                        .username(username)
                        .password(user.getPassword())
                        .build()
        ).orElseThrow(() -> new UsernameNotFoundException(
            "User with username [%s] not found".formatted(username)));
    }

}
