package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.CMRespDto;
import com.sparta.reserq.domain.dto.PostsReqDto;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.config.jwt.UserDetailsImpl;
import com.sparta.reserq.service.LikesService;
import com.sparta.reserq.service.PostsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService postsService;
    private final LikesService likesService;

    @PostMapping("/create")
    public String createPost(@RequestBody PostsReqDto postsReqDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        postsService.savePost(postsReqDto.getTitle(),postsReqDto.getContent(),userId);
        return userId.toString();
    }

    @PostMapping("/{postsId}/likes")
    public ResponseEntity<?>postslikes(@PathVariable("postsId") Long postsId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likesService.postLike(postsId,userDetails.getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요성공",null),HttpStatus.CREATED);
    }

    @DeleteMapping("/{postsId}/likes")
    public ResponseEntity<?>unPostslikes(@PathVariable("postsId") Long postsId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likesService.unPostLike(postsId,userDetails.getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소성공",null),HttpStatus.OK);
    }







    //===========================================================
    //===========================================================



    @GetMapping
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postsService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Posts>> getPostsByUserId(@PathVariable Long userId) {
        List<Posts> posts = postsService.getPostsByUserId(userId);
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(
            @PathVariable Long id,
            @RequestBody PostsReqDto postsReqDto,
            @AuthenticationPrincipal UserDetailsImpl principalDetails) {
        try {
            postsService.updatePost(id, postsReqDto);
            return ResponseEntity.ok("게시글이 업데이트되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            postsService.deletePost(id, userDetails);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}