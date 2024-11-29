package com.iplacex.mcsv_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRegisterRequestDto {

    private String email;
    private String username;
    private String password;

}
