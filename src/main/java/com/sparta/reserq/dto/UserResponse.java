package com.sparta.reserq.dto;

import com.sparta.reserq.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserResponse {
    @Getter
    @Setter
    public static class DetailOutDTO {
        private Long id;
        private String email;
        private String username;
        private String password;
        private String profileImageUrl;
        private String greeting;
        private String roles;

        public DetailOutDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.profileImageUrl = user.getProfileImageUrl();
            this.greeting = user.getGreeting();
            this.roles = user.getRoles();
        }
    }

    @Setter
    @Getter
    public static class JoinOutDTO {
        private Long id;
        private String email;


        public JoinOutDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();


        }
    }

}