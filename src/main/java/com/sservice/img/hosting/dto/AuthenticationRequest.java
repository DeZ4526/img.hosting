package com.sservice.img.hosting.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
