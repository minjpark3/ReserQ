package com.sparta.reserq.service;

import com.sparta.reserq.domain.follower.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

