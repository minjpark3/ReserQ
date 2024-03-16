package com.example.mo_activity.controller;


import com.example.mo_activity.domain.dto.CommentDto;
import com.example.mo_activity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<Long> createComment(
            @RequestBody CommentDto commentDto) {
        try {
            Long commentId = commentService.create(commentDto);
            return ResponseEntity.ok(commentId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}