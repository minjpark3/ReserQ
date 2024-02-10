package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.FollowerDto;
import com.example.mo_activity.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/api/follower/{toUserId}")
    public ResponseEntity<String> followUser(@PathVariable("toUserId") Long toUserId, @RequestBody Map<String, Long> requestBody) {
        Long fromUserId = requestBody.get("fromUserId");

        try {
            followerService.follow(fromUserId, toUserId);
            return ResponseEntity.ok("팔로우가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/api/follower/{toUserId}")
    public ResponseEntity<String> unsubscribe(@RequestBody FollowerDto followerDto, @PathVariable(name = "toUserId") Long userId) {
        Long currentUserId = followerDto.getUserId();
        followerService.unfollow(currentUserId, userId);
        return ResponseEntity.ok("언팔됨");
    }
}

