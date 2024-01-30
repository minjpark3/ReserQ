package com.sparta.reserq.domain.posts;

import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content; // ex)나 오늘 너무 피곤하다~

    // @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; //한명의 유저는 N개의 포스트가능 1개의 포스트는 1명의 유저

    //    @JsonIgnoreProperties({"posts"})
//    @OneToMany(mappedBy = "posts")
//    private List<Likes> likes;
//
//    @OrderBy("id DESC")
//    @JsonIgnoreProperties({"posts"})
//    @OneToMany(mappedBy = "posts")
//    private List<Comment>comments;
    private LocalDateTime createDate;

    public Posts(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }


    @PrePersist //디비에 insert되기 전에 실행
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }


}
