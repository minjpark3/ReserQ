package com.example.mo_user.domain.dto;

import lombok.Getter;

@Getter
public class VerificationRequest {

    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
