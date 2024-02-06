package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.CMRespDto;
import com.sparta.reserq.config.jwt.UserDetailsImpl;
import com.sparta.reserq.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/api/follower/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "toUserId") Long toUserId) throws Exception {
        followerService.follow(userDetails.getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우됨", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/follower/{toUserId}")
    public ResponseEntity<?> unsubscribe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "toUserId") Long toUserId) {
        followerService.unfollow(userDetails.getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "언팔됨", null), HttpStatus.OK);
    }
}

