package com.iplacex.mcsv_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private long id;
    private String nombre;
    private String email;
    private ZoneDto zone;
    
}
