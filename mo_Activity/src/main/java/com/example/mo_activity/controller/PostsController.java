package com.example.mo_activity.controller;

import com.example.mo_activity.domain.dto.PostsReqDto;
import com.example.mo_activity.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService postsService;
//    private final LikesService likesService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostsReqDto postsReqDto) {
        postsService.savePost(postsReqDto);
        return ResponseEntity.ok().body("포스트 생성완료");
    }
}
//    @PostMapping("/{postsId}/likes")
//    public ResponseEntity<?>postslikes(@PathVariable("postsId") Long postsId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        likesService.postLike(postsId,userDetails.getId());
//        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요성공",null),HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{postsId}/likes")
//    public ResponseEntity<?>unPostslikes(@PathVariable("postsId") Long postsId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        likesService.unPostLike(postsId,userDetails.getId());
//        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소성공",null),HttpStatus.OK);
//    }







    //===========================================================
    //===========================================================

//
//
//    @GetMapping
//    public ResponseEntity<List<Posts>> getAllPosts() {
//        List<Posts> posts = postsService.getAllPosts();
//        return ResponseEntity.ok(posts);
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Posts>> getPostsByUserId(@PathVariable Long userId) {
//        List<Posts> posts = postsService.getPostsByUserId(userId);
//        if (!posts.isEmpty()) {
//            return ResponseEntity.ok(posts);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updatePost(
//            @PathVariable Long id,
//            @RequestBody PostsReqDto postsReqDto,
//            @AuthenticationPrincipal UserDetailsImpl principalDetails) {
//        try {
//            postsService.updatePost(id, postsReqDto);
//            return ResponseEntity.ok("게시글이 업데이트되었습니다.");
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePost(
//            @PathVariable Long id,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        try {
//            postsService.deletePost(id, userDetails);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}