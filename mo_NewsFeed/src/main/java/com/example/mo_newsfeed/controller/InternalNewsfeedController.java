package com.example.mo_newsfeed.controller;


import com.example.mo_newsfeed.newsFeed.NewsFeedDto;
import com.example.mo_newsfeed.service.NewsFeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/internal/newsfeeds")
public class InternalNewsfeedController {
    private final NewsFeedService newsFeedService;

    public InternalNewsfeedController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody NewsFeedDto newsFeedDto
    ) {
        newsFeedService.create(newsFeedDto);
        return ResponseEntity.ok().body("뉴스피드 생성 성공");
    }
}