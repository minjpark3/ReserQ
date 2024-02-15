package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.FollowerDto;
import com.example.mo_activity.service.FollowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;

    // POST 요청을 처리하는 메서드
    @PostMapping("/api/follower")
    public ResponseEntity<Void> follow(
            @RequestBody FollowerDto followerDto) {
        try {
            followerService.follow(followerDto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 언팔로우 기능을 담당하는 메서드
    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long fromUserId, @RequestParam Long toUserId) {
            followerService.unfollow(fromUserId, toUserId);
            return ResponseEntity.ok("언팔로우되었습니다.");
    }
}

