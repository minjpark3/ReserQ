package com.sparta.reserq.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class SignupDto {
    @NotBlank(message = "email은 비어서는 안됩니다.")
    private String email;

    @NotBlank(message = "username은 비어서는 안됩니다.")
    private String username;

    @NotBlank(message = "password는 비어서는 안됩니다.")
    private String password;
    @NotBlank
    private String profileImageUrl;//프로필이미지
    @NotBlank
    private String greeting; //인사말
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .greeting(greeting)
                .build();
    }
}