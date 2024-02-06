package com.sparta.reserq.domain.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String name;
    private String profileImageUrl;
    private String greeting;

    public User toEntity(){
        return User.builder()
                .name(name)
                .profileImageUrl(profileImageUrl)
                .greeting(greeting)
                .build();
    }

}
