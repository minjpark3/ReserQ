package com.sparta.reserq.domain.comment;
import com.sparta.reserq.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1000, nullable = false)
    private String text;

    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist //디비에 insert되기 전에 실행
    private void createDate(){
        this.created_at=LocalDateTime.now();
    }

}
