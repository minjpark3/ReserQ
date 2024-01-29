package com.sparta.reserq.controller;

import com.sparta.reserq.dto.PostUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//public class PostController {
//    private final PostService postService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Void> postUpload(@RequestBody PostUploadDto postUploadDto, Authentication authentication) {
//        if (authentication != null) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof PrincipalDetails) {
//                PrincipalDetails principalDetails = (PrincipalDetails) principal;
//                postService.postUpload(postUploadDto, principalDetails);
//                return new ResponseEntity<>(HttpStatus.CREATED);
//            }
//        }
//        // authentication이 null이거나 PrincipalDetails가 아닌 경우에 대한 처리
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 예시로 401 에러 코드 반환
//    }
//}
