package com.example.mo_activity.service;

import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.dto.PostsReqDto;
import com.example.mo_activity.domain.posts.Posts;
import com.example.mo_activity.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserClient userClient;

    @Transactional
    public void savePost(PostsReqDto postsReqDto) {
        Long userId = postsReqDto.getUserId();
        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("user not exists");
        }
        Posts newPosts = new Posts(
                userId,
                postsReqDto.getTitle(),
                postsReqDto.getContent()
        );
        Posts createPost = postsRepository.save(newPosts);
    }

}

