package com.sparta.reserq.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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