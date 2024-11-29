package com.iplacex.mcsv_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthLoginRequestDto {

    private String username;
    private String password;

    
}
