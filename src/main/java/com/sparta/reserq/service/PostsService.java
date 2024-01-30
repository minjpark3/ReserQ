package com.sparta.reserq.service;

import com.sparta.reserq.domain.dto.PostsReqDto;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.posts.PostsRepository;
import com.sparta.reserq.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserService userService;
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    public Posts getPostById(Long id) {
        return postsRepository.findById(id).orElse(null);
    }

    public Posts createPost(PostsReqDto postRequest, User currentUser) {
        // 현재 로그인한 사용자의 정보를 받아서 사용합니다.
        if (currentUser.getId() == null) {
            throw new IllegalArgumentException("Current user ID cannot be null");
        }

        // 현재 로그인한 사용자의 정보를 이용하여 포스트를 생성합니다.
        Posts post = new Posts(postRequest.getTitle(), postRequest.getContent(), currentUser);
        return postsRepository.save(post);
    }

    public Posts updatePost(Long id, Posts updatedPost) {
        if (postsRepository.existsById(id)) {
            updatedPost.setId(id);
            return postsRepository.save(updatedPost);
        } else {
            return null; // 게시글이 존재하지 않을 경우 처리
        }
    }

    public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }
}
