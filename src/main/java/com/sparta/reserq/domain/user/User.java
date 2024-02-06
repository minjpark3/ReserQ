package com.sparta.reserq.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
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

}