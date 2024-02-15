package com.example.mo_newsfeed.controller;

import com.example.mo_newsfeed.newsFeed.NewsFeed;
import com.example.mo_newsfeed.newsFeed.NewsFeedResDto;
import com.example.mo_newsfeed.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NewsFeedController {
    private final NewsFeedService newsFeedService;

//    @GetMapping("/api/newsfeed/{userId}")
//    public ResponseEntity<Page<NewsFeedResDto>> myNewsfeed(
//            @PathVariable Long userId,
//            @RequestParam(value = "orderBy", defaultValue = "createDate") String orderBy,
//            @RequestParam(value = "sortBy", defaultValue = "desc") String sortBy,
//            @RequestParam(value = "pageCount", defaultValue = "20") int size,
//            @RequestParam(value = "page", defaultValue = "0") int page
//    ) {
//        Sort.Direction direction = sortBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//        Sort sort = Sort.by(direction, orderBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Long principalId = Long.valueOf(userId);
//        Page<NewsFeedResDto> newsfeeds = newsFeedService.myNewsfeed(principalId, pageable)
//                .map(NewsFeedResDto::from);
//        return ResponseEntity.ok().body(newsfeeds);
//    }
//    @GetMapping("/api/newsfeed/{userId}")
//    public ResponseEntity<Page<NewsFeedResDto>> myNewsfeed(
//            @PathVariable Long userId
//    ) {
//
//        Long principalId = Long.valueOf(userId);
//        Page<NewsFeedResDto> newsfeeds = newsFeedService.myNewsfeed(principalId)
//                .map(NewsFeedResDto::from);
//        return ResponseEntity.ok().body(newsfeeds);
//    }

    @GetMapping("/api/newsfeed/{userId}")
    public ResponseEntity<List<NewsFeedResDto>> getUserNewsfeed(@PathVariable Long userId) {
        List<NewsFeedResDto> userNewsfeed = newsFeedService.getUserNewsfeed(userId);
        return ResponseEntity.ok(userNewsfeed);
    }
}