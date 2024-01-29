package com.sparta.reserq.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
    @Setter
    @Getter
    public static class LoginInDTO {
        //@Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        @NotEmpty
        private String email;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
    }

    @Setter
    @Getter
    public static class JoinInDTO {
        @NotEmpty
       // @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        private String email;

        @NotEmpty
        private String username;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        @NotEmpty
        private String profileImageUrl;

        @NotEmpty
        private String greeting;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .profileImageUrl(profileImageUrl)
                    .greeting(greeting)
                    .roles("ROLE_USER")
                    .verified(true)
                    .build();
        }
    }
}