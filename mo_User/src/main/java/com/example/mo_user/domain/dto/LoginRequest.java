package com.example.mo_user.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginRequest {

    private String email;
    private String password;

    @JsonCreator
    public LoginRequest(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}