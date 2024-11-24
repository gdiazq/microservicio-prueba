package com.iplacex.mcsv_zones.service;

import java.util.Optional;

import com.iplacex.mcsv_zones.model.ZoneEntity;

public interface ZoneService {

    Iterable<ZoneEntity> findAll();

    Optional <ZoneEntity> findById(Long id);

    ZoneEntity save(ZoneEntity zoneEntity);

}
