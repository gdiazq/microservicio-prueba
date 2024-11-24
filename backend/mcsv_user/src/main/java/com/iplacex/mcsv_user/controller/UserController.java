package com.iplacex.mcsv_user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iplacex.mcsv_user.client.ZoneServiceClient;
import com.iplacex.mcsv_user.dto.UserDto;
import com.iplacex.mcsv_user.dto.ZoneDto;
import com.iplacex.mcsv_user.model.UserEntity;
import com.iplacex.mcsv_user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ZoneServiceClient zoneServiceClient;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<UserEntity> userOptional = this.userService.findById(id);
        return userOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        try {
            ZoneDto zone = zoneServiceClient.getZoneById(userDto.getZoneId());

            UserEntity userEntity = UserEntity.builder()
                    .nombre(userDto.getNombre())
                    .email(userDto.getEmail())
                    .zoneId(zone.getId())  // Guarda el ID de la zona
                    .build();

            UserEntity createdUser = userService.save(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Zone not found");
        }
    }

}
