package com.example.mo_user.domain.dto;

import com.example.mo_user.domain.user.User;
import lombok.Getter;

@Getter
public class UserUpdateDto {
    private String name;
    private String profileImageUrl;
    private String greeting;

    public UserUpdateDto(String name, String profileImageUrl, String greeting) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.greeting = greeting;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .profileImageUrl(profileImageUrl)
                .greeting(greeting)
                .build();
    }

}
