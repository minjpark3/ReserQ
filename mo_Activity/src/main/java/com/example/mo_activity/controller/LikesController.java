package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.LikesDto;
import com.example.mo_activity.service.LikesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/api/posts/likes/{postsId}")
    public ResponseEntity<Void> createLike(@PathVariable Long postsId, @RequestBody LikesDto likesDto) {
        try {
            likesService.create(postsId, likesDto);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}