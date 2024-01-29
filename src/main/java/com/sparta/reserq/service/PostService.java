package com.sparta.reserq.service;

import com.sparta.reserq.domain.post.Post;
import com.sparta.reserq.domain.post.PostRepository;
import com.sparta.reserq.dto.PostUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@RequiredArgsConstructor
//@Service
//public class PostService {
//    private  final PostRepository postRepository;
//
//    @Transactional
//    public void postUpload(PostUploadDto postUploadDto, PrincipalDetails principalDetails) {
//        // 테이블에 저장
//        Post post = postUploadDto.toEntity(postUploadDto.getTitle(), postUploadDto.getContent(), principalDetails.getUser());
//        Post postEntity = postRepository.save(post);
//    }
//}

