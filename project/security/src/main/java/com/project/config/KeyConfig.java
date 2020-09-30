package com.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

@Component
@ConfigurationProperties(prefix = "spring.jwt.key")
public class KeyConfig {


    private String pub ;
    private String pri ;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public PublicKey getPublicKey(){

        return publicKey;
    }

    public PrivateKey getPrivateKey(){

        return privateKey;
    }
}
