package com.sparta.reserq.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String email;
    private String username;
    private String password;
    private String profileImageUrl;
    private String greeting;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .profileImageUrl(profileImageUrl)
                .greeting(greeting)
                .build();

    }
}
