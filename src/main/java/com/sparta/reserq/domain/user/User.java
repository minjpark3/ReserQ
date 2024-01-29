package com.sparta.reserq.domain.user;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tb")
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
    private String roles;

    private LocalDateTime created_at;

    @PrePersist
    private void created_at(){
        this.created_at=LocalDateTime.now();
    }


    @CreationTimestamp
    private LocalDateTime updated_at;

    private boolean verified; // 이메일 인증 여부

    private String verificationToken; // 이메일 인증을 위한 토큰

    public List<String>getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


}
