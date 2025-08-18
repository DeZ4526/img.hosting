package com.sservice.img.hosting.dto;

import lombok.Getter;

public class AuthenticationResponse {
    @Getter
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
