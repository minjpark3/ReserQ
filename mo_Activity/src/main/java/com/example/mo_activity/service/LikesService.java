package com.example.mo_activity.service;

import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
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
        String type = "POSTSLIKES";

        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }

        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EntityNotFoundException("포스트를 찾을수 없습니다."));

        PostsLikes newLike = new PostsLikes(userId, postsId); // activityId 대신 postsId 사용
        postsLikesRepository.save(newLike);

        NewsfeedClientReq newsfeedClientReq = new NewsfeedClientReq(
                userId,
                type,
                postsId
        );
        newsfeedClient.create(newsfeedClientReq);
    }

    @Transactional
    public void unPostLike(Long postsId, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("사용자 ID가 유효하지 않습니다.");
        }
        postsLikesRepository.mUnLikes(postsId, userId);
    }
}
