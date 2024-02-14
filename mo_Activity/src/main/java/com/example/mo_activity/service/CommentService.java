package com.example.mo_activity.service;

import com.example.mo_activity.CustomValidationApiException;
import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.comment.Comment;
import com.example.mo_activity.domain.comment.CommentRepository;
import com.example.mo_activity.domain.dto.CommentDto;
import com.example.mo_activity.domain.posts.Posts;
import com.example.mo_activity.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;
    private final UserClient userClient;
    private final NewsfeedClient newsfeedClient;

    @Transactional
    public void create(CommentDto commentDto) {
        Long userId = commentDto.getUserId();
        String type = "COMMENT";
        Posts posts = postsRepository.findById(commentDto.getPostsId())
            .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("유저를 찾을수 없습니다.");
        }

        Comment newComment = Comment.builder()
                .userId(userId)
                .posts(posts)
                .content(commentDto.getContent())
                .build();

        commentRepository.save(newComment);

        // 뉴스피드 요청 DTO 생성
        NewsfeedClientReq newsfeedClientReq = new NewsfeedClientReq(
                userId,
                type,
                posts.getId()
        );
        newsfeedClient.create(newsfeedClientReq);

    }

}