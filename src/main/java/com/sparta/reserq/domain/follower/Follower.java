package com.sparta.reserq.domain.follower;
import com.sparta.reserq.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@JoinColumn(name = "followerId")
    @ManyToOne
    private User fromUser;

   // @JoinColumn(name = "followingId")
    @ManyToOne
    private User toUser;

    private LocalDateTime created_at;

    @PrePersist
    private void createDate(){
        this.created_at=LocalDateTime.now();
    }

}