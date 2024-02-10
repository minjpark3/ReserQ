package com.example.mo_activity.service;

import com.example.mo_activity.CustomValidationApiException;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.comment.Comment;
import com.example.mo_activity.domain.comment.CommentRepository;
import com.example.mo_activity.domain.dto.CommentDto;
import com.example.mo_activity.domain.posts.Posts;
import com.example.mo_activity.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;
    private final UserClient userClient;

    @Transactional
    public Comment createComment(CommentDto commentDto) {
        Long userId = commentDto.getUserId();
        Posts posts = postsRepository.findById(commentDto.getPostsId())
                .orElseThrow(() -> new CustomValidationApiException("게시물을 찾을수 없습니다."));

        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("user not exists");
        }

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .posts(posts)
                .userId(userId)
                .build();

        return commentRepository.save(comment);
    }
}