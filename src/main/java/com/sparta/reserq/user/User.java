package com.sparta.reserq.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true)
    private String email;
    private String username;
    private String password;
    private String profileImageUrl;
    private String greeting;

    @CreationTimestamp
    private Timestamp created_at;
    @CreationTimestamp
    private Timestamp updated_at;
}
