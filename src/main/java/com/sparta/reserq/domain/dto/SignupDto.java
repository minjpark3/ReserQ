package com.sparta.reserq.domain.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class SignupDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2)
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "프로필 사진을 올려주세요")
    private String profileImageUrl;//프로필이미지
    @NotBlank(message = "인사말을 남겨주세요")
    private String greeting; //인사말

    private boolean verified;
    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .profileImageUrl(profileImageUrl)
                .greeting(greeting)
                .verified(verified)
                .build();
    }
}