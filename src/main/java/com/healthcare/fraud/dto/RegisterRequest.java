package com.healthcare.fraud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // ROLE_HOSPITAL, ROLE_ADMIN
}
