package com.sparta.reserq.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;


    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String name;//이름
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String profileImageUrl;//프로필이미지
    @Column(nullable = false)
    private String greeting; //인사말

    private LocalDateTime createDate;

    @PrePersist
    private void created_at(){
        this.createDate=LocalDateTime.now();
    }


    @CreationTimestamp
    private LocalDateTime updateData;
    @Transient
    private boolean verified; // 이메일 인증 여부

    private String verificationToken; // 이메일 인증을 위한 토큰
    @Transient
    private String role;

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String name, String profileImageUrl, String greeting) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.greeting = greeting;
    }
    public void update(String name, String profileImageUrl, String greeting) {
        if (name != null) {
            this.name = name;
        }
        if (profileImageUrl != null) {
            this.profileImageUrl = profileImageUrl;
        }
        if (greeting != null) {
            this.greeting = greeting;
        }
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}