package com.example.mo_activity.domain.follower;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Follower",
                        columnNames = {"fromUserId","toUserId"}
                )
        }
)
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "fromUserId")
    private Long fromUserId;


    @Column(name = "toUserId")
    private Long toUserId;

    @Column(name = "createDate")
    private LocalDateTime createDate;
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }
}