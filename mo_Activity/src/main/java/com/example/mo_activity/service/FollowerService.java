package com.example.mo_activity.service;

import com.example.mo_activity.domain.follower.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;

    @Transactional
    public void follow(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        if (isFollowing(fromUserId, toUserId)) {
            throw new IllegalArgumentException("이미 팔로우한 사용자입니다.");
        }

        followerRepository.mSubscribe(fromUserId, toUserId);
    }

    @Transactional
    public void unfollow(Long fromUserId, Long toUserId) {
        followerRepository.mUnSubscribe(fromUserId, toUserId);
    }

    private boolean isFollowing(Long fromUserId, Long toUserId) {
        return followerRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId);
    }
}
