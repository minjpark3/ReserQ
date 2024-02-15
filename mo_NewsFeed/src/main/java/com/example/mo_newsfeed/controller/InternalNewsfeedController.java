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
    public ResponseEntity<Void> create(
            @RequestBody NewsFeedDto request
    ) {
        newsFeedService.create(request);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("/newsfeeds")
//    public ResponseEntity<Page<NewsFeed>> getNewsfeeds(Long userId, List<Long> toUserIds) {
//        Page<NewsFeed> newsfeeds = newsFeedService.filterNewsfeeds(userId, toUserIds);
//        return ResponseEntity.ok(newsfeeds);
//    }
//    public ResponseEntity<List<Long>> getUserToUserIds(@PathVariable("userId") Long userId) {
//        List<Long> toUserIds = newsFeedService.getUserToUserIds(userId);
//        return ResponseEntity.ok(toUserIds);
//    }

}