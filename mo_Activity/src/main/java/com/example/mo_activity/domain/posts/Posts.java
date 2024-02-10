package com.example.mo_activity.domain.posts;

import com.example.mo_activity.domain.dto.PostsReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postsId")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;


    private LocalDateTime createDate;
    @PrePersist //디비에 insert되기 전에 실행
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }
    @CreationTimestamp
    private LocalDateTime updateData;

    @Builder
    public Posts(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public static Posts create(PostsReqDto postsReqDto){
        return Posts.builder()
                .userId(postsReqDto.getUserId())
                .title(postsReqDto.getTitle())
                .content(postsReqDto.getContent())
                .build();
    }
}
