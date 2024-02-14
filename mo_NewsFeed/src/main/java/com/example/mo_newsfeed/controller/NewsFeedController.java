package com.example.mo_newsfeed.controller;

import com.example.mo_newsfeed.newsFeed.NewsFeedResDto;
import com.example.mo_newsfeed.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NewsFeedController {
    private final NewsFeedService newsFeedService;

    @GetMapping("/api/newsfeed/{userId}")
    public ResponseEntity<List<NewsFeedResDto>> getUserNewsfeed(@PathVariable Long userId){
        List<NewsFeedResDto> newsfeeds = newsFeedService.getUserNewsfeed(userId);
        return ResponseEntity.ok(newsfeeds);
    }
}
