package com.iplacex.mcsv_user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iplacex.mcsv_user.client.ZoneServiceClient;
import com.iplacex.mcsv_user.dto.UserDto;
import com.iplacex.mcsv_user.dto.ZoneDto;
import com.iplacex.mcsv_user.model.UserEntity;
import com.iplacex.mcsv_user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private ZoneServiceClient zoneServiceClient;

    @Override
    @Transactional(readOnly = true)
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllWithZone() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> {
            ZoneDto zone;
            try {
                zone = zoneServiceClient.getZoneById(user.getZoneId());
            } catch (Exception e) {
                log.error("Error al obtener la zona del usuario con ID: {}", user.getId());
                zone = new ZoneDto();
            }
            return new UserDto(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                zone
            );
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
    

}
