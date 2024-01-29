package com.sparta.reserq.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

//    @OrderBy("id DESC")
//    @JsonIgnoreProperties({"posts"})
//    @OneToMany(mappedBy = "posts")
//    private List<Comment> comments;

    @Transient //DB에 컬럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient
    private int likeCount;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    private void createDate(){
        this.created_at=LocalDateTime.now();
    }
}
