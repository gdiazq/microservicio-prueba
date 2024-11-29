package com.iplacex.mcsv_auth.jwt;

import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import com.nimbusds.jose.jwk.RSAKey;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.util.Base64;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;

@Configuration
public class JwtConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${jwt.private-key}")
    private String privateKeyPath;

    @Value("${jwt.public-key}")
    private String publicKeyPath;

    @Value("${jwt.ttl}")
    private Duration ttl;

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    @PostConstruct
    public void init() {
        try {
            this.privateKey = loadPrivateKey(privateKeyPath);
            this.publicKey = loadPublicKey(publicKeyPath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA keys", e);
        }
    }

    private RSAPrivateKey loadPrivateKey(String path) throws Exception {
        // Leer el contenido del archivo de clave privada
        String keyContent = new String(Files.readAllBytes(Path.of(resourceLoader.getResource(path).getURI())));
        
        // Limpiar el contenido de la clave privada
        String keyTrimmed = keyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", ""); // Eliminar espacios en blanco
    
        // Decodificar la clave privada
        byte[] decodedBytes = Base64.getMimeDecoder().decode(keyTrimmed);
    
        // Crear la especificación de clave a partir de los bytes decodificados
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        // Generar y devolver la clave privada
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpecPKCS8);
    }

    private RSAPublicKey loadPublicKey(String path) throws Exception {
        // Leer el contenido del archivo de clave pública
        String keyContent = new String(Files.readAllBytes(Path.of(resourceLoader.getResource(path).getURI())));
        
        // Limpiar el contenido de la clave pública
        String keyTrimmed = keyContent
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", ""); // Eliminar espacios en blanco

        // Decodificar la clave pública
        byte[] decodedBytes = Base64.getMimeDecoder().decode(keyTrimmed);

        // Crear la especificación de clave a partir de los bytes decodificados
        X509EncodedKeySpec keySpecX509B = new X509EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        // Generar y devolver la clave pública
        return (RSAPublicKey) keyFactory.generatePublic(keySpecX509B);
    }

    @Bean
    JwtEncoder jwtEncoder() {
        final var jwk = new RSAKey.Builder(publicKey)
            .privateKey(privateKey).build();

        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    JwtService jwtService(@Value("${spring.application.name}") final String appName, final JwtEncoder jwtEncoder) {
        return new JwtService(appName, ttl, jwtEncoder);
    }
}