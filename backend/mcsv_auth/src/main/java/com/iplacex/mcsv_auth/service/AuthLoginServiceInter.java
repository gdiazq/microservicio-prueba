package com.iplacex.mcsv_auth.service;

import com.iplacex.mcsv_auth.dto.AuthLoginRequestDto;
import com.iplacex.mcsv_auth.dto.AuthLoginResponseDto;

public interface AuthLoginServiceInter {

    AuthLoginResponseDto authenticate(final AuthLoginRequestDto request);

}
