package com.example.mo_activity.service;

import com.example.mo_activity.client.NewsfeedClient;
import com.example.mo_activity.client.NewsfeedClientReq;
import com.example.mo_activity.client.UserClient;
import com.example.mo_activity.domain.dto.FollowerDto;
import com.example.mo_activity.domain.follower.Follower;
import com.example.mo_activity.domain.follower.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final NewsfeedClient newsfeedClient;
    private final UserClient userClient;

    @Transactional
    public void follow(FollowerDto followerDto) {
        Long fromUserId = followerDto.getUserId();
        Long toUserId = followerDto.getToUserId();
        String type = "FOLLOWER";

        if (Objects.equals(fromUserId, toUserId)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        if (!userClient.checkUserExists(fromUserId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        if (!userClient.checkUserExists(toUserId)) {
            throw new IllegalArgumentException("팔로워 할 사용자를 찾을 수 없습니다");
        }

        Follower follower = Follower.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();

       followerRepository.save(follower);


        // 뉴스피드 요청 DTO 생성
        NewsfeedClientReq newsfeedClientReq =new NewsfeedClientReq(
                fromUserId,
                type,
                toUserId
        );
        newsfeedClient.create(newsfeedClientReq);
    }


    @Transactional
    public void unfollow(Long fromUserId, Long toUserId) {
        followerRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId);
    }


    public List<Long> findByFollowingId(Long fromUserId) {
        return followerRepository.findByFromUserId(fromUserId).stream()
                .map(Follower::getToUserId)
                .toList();
    }
}
