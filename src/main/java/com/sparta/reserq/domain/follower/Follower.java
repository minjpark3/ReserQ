package com.sparta.reserq.domain.follower;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"fromUserId","toUserId"}
                )
        }
)
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }

}