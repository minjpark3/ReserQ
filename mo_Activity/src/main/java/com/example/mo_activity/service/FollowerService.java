package com.example.mo_activity.service;

import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.ActivityType;
import com.example.mo_activity.domain.dto.FollowerDto;
import com.example.mo_activity.domain.follower.Follower;
import com.example.mo_activity.domain.follower.FollowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final NewsfeedClient newsfeedClient;
    private final UserClient userClient;

    @Transactional
    public void follow(FollowerDto followerDto) {
        Long userId = followerDto.getUserId();
        Long toUserId = followerDto.getToUserId();

        if (Objects.equals(userId, toUserId)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        if (!userClient.checkUserExists(userId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        if (!userClient.checkUserExists(toUserId)) {
            throw new IllegalArgumentException("팔로워 할 사용자를 찾을 수 없습니다");
        }

        Follower follower = Follower.builder()
                .fromUserId(userId)
                .toUserId(toUserId)
                .build();

        Follower saved =followerRepository.save(follower);
        ActivityType activityType = ActivityType.fromString("FOLLOWER");

        // 뉴스피드 요청 DTO 생성
        NewsfeedClientReq newsfeedClientReq =NewsfeedClientReq.builder()
                .userId(userId)
                .relatedUserId(toUserId)
                .activityId(saved.getId())
                .activityType("FOLLOWER")
                .build();

        newsfeedClient.create(newsfeedClientReq);
    }


    @Transactional
    public void unfollow(Long fromUserId, Long toUserId) {
        followerRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId);
    }


    public List<Long> findByFollowingId(Long userId) {
        return followerRepository.findByFromUserId(userId).stream()
                .map(Follower::getToUserId)
                .toList();
    }
}
