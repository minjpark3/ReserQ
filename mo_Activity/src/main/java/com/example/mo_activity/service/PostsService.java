package com.example.mo_activity.service;

import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.dto.PostsReqDto;
import com.example.mo_activity.domain.posts.Posts;
import com.example.mo_activity.domain.posts.PostsRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Builder
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserClient userClient;
    private final NewsfeedClient newsfeedClient;

    @Transactional
    public void create(PostsReqDto postsReqDto) {
        Long userId = postsReqDto.getUserId();
        String type = "POSTS";
        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        Posts newPosts = new Posts(
                userId,
                postsReqDto.getTitle(),
                postsReqDto.getContent()
        );
        Posts createPost = postsRepository.save(newPosts);

    NewsfeedClientReq newsfeedClientReq =new NewsfeedClientReq(
            userId,
            type,
            createPost.getId()

    );
        newsfeedClient.create(newsfeedClientReq);
    }
}

