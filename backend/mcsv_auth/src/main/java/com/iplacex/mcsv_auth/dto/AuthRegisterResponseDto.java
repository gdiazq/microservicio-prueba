package com.iplacex.mcsv_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRegisterResponseDto {

    private String username;
    private String email;

}
