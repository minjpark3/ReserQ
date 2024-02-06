package com.sparta.reserq.controller;

import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/NewsFeed")
public class NewsFeedController {
    private final NewsFeedService newsFeedService;

    @GetMapping("/followed-posts/{userId}")
    public ResponseEntity<List<Posts>> getFollowedPosts( @PathVariable(name = "userId") Long userId) {
        List<Posts> followedPosts = newsFeedService.getFollowedPosts(userId);
        return new ResponseEntity<>(followedPosts, HttpStatus.OK);
    }
}
