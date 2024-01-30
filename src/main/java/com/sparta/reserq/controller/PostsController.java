package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.PostsReqDto;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.posts.PostsRepository;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.service.PostsService;
import com.sparta.reserq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService postsService;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postsService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posts> getPostById(@PathVariable Long id) {
        Posts post = postsService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Posts> createPost(@RequestBody PostsReqDto postRequest, Authentication authentication) {
        try {
            // 현재 로그인한 사용자의 정보를 가져옵니다.
            User currentUser = (User) authentication.getPrincipal();

            if (currentUser.getId() == null) {
                throw new IllegalArgumentException("Current user ID cannot be null");
            }

            Posts createdPost = postsService.createPost(postRequest, currentUser);
            return ResponseEntity.ok(createdPost);
        } catch (IllegalArgumentException e) {
            // User ID가 null이거나 사용자를 찾을 수 없는 경우
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long id, @RequestBody Posts updatedPost) {
        Posts post = postsService.updatePost(id, updatedPost);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postsService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}