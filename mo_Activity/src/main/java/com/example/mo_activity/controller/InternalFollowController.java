package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.FollowerDto;
import com.example.mo_activity.service.FollowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internal")
public class InternalFollowController {
    private final FollowerService followerService;

    public InternalFollowController(FollowerService followerService) {
        this.followerService = followerService;
    }


    @GetMapping("/follows")
    public ResponseEntity<List<Long>> findFollowing(@RequestParam(name = "userId") Long fromUserId) {
        return ResponseEntity.ok().body(followerService.findByFollowingId(fromUserId));
    }
}