package com.project.service;

import com.project.security.TokenVerifyFilter;
import org.springframework.stereotype.Service;

@Service
public class VerifyTokenService {


    public String verify(String token) {

        return TokenVerifyFilter.verify(token);
    }
}
