package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.LikesDto;
import com.example.mo_activity.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/api/posts/{postsId}/likes")
    public ResponseEntity<?> postslikes(@PathVariable("postsId") Long postsId, @RequestBody LikesDto likesDto) {
        Long userId = likesDto.getUserId();

        likesService.postLike(postsId, userId);
        return ResponseEntity.ok().body("좋아요 성공");
    }

    @DeleteMapping("/api/posts/{postsId}/likes")
    public ResponseEntity<?> unPostslikes(@PathVariable("postsId") Long postsId, @RequestBody LikesDto likesDto) {
        Long userId = likesDto.getUserId();

        likesService.unPostLike(postsId, userId);
        return ResponseEntity.ok().body("좋아요 취소 성공");
    }
}