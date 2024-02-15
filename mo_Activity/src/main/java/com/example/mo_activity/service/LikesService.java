package com.example.mo_activity.service;

import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.ActivityType;
import com.example.mo_activity.domain.dto.LikesDto;
import com.example.mo_activity.domain.likes.PostsLikes;
import com.example.mo_activity.domain.likes.PostsLikesRepository;
import com.example.mo_activity.domain.posts.Posts;
import com.example.mo_activity.domain.posts.PostsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LikesService {
    private final UserClient userClient;
    private final PostsRepository postsRepository;
    private final PostsLikesRepository postsLikesRepository;
    private final NewsfeedClient newsfeedClient;

    @Transactional
    public void create(Long postsId, LikesDto likesDto) {
        Long userId = likesDto.getUserId();
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EntityNotFoundException("포스트를 찾을수 없습니다."));

        if (postsLikesRepository.existsByPostsIdAndUserId(postsId, userId)) {
            throw new IllegalArgumentException("이미 좋아요 했습니다.");
        }

        PostsLikes newLike = PostsLikes.builder()
                .posts(posts)
                .status(true)
                .userId(userId)
                .build();

        PostsLikes saved = postsLikesRepository.save(newLike);
        ActivityType activityType = ActivityType.fromString("POSTSLIKES");

        NewsfeedClientReq newsfeedClientReq =NewsfeedClientReq.builder()
                .userId(userId)
                .relatedUserId(posts.getUserId())
                .activityId(saved.getId())
                .activityType("POSTSLIKES")
                .build();

        newsfeedClient.create(newsfeedClientReq);


    }

}
