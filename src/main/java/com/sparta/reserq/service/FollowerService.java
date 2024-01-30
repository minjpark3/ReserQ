package com.sparta.reserq.service;

import com.sparta.reserq.domain.dto.CMRespDto;
import com.sparta.reserq.domain.follower.Follower;
import com.sparta.reserq.domain.follower.FollowerRepository;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.posts.PostsRepository;
import com.sparta.reserq.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class FollowerService {
    private final FollowerRepository followerRepository;
    @Transactional
    public void follow(Long fromUserId, Long toUserId) throws Exception {
        try{
            followerRepository.mSubscribe(fromUserId, toUserId);
        }catch (Exception e){
            throw new Exception("이미 팔로우중입니다.");
        }
    }

    @Transactional
    public void unfollow(Long fromUserId, Long toUserId) {
        followerRepository.mUnSubscribe(fromUserId, toUserId);
    }
}

