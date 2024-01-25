package com.sparta.reserq.domain.post;

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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    private void createDate(){
        this.created_at=LocalDateTime.now();
    }
}
