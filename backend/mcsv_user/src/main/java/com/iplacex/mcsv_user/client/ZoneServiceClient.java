package com.iplacex.mcsv_user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iplacex.mcsv_user.dto.ZoneDto;

@FeignClient(name = "mcsv-zones")
public interface ZoneServiceClient {

    @GetMapping("/zones/{id}")
    ZoneDto getZoneById(@PathVariable Long id);
    
}
