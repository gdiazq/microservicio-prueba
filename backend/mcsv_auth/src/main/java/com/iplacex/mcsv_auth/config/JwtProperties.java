package com.iplacex.mcsv_auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String privateKey;
    private String publicKey;
    private String ttl;

    public JwtProperties(String privateKey, String publicKey, String ttl) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.ttl = ttl;
    }

}
