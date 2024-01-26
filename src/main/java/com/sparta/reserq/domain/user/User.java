package com.sparta.reserq.domain.user;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String username;//이름
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String profileImageUrl;//프로필이미지
    @Column(nullable = false)
    private String greeting; //인사말

    private LocalDateTime created_at;

    @PrePersist
    private void created_at(){
        this.created_at=LocalDateTime.now();
    }


    @CreationTimestamp
    private LocalDateTime updated_at;

    private boolean verified; // 이메일 인증 여부

    private String verificationToken; // 이메일 인증을 위한 토큰



}
