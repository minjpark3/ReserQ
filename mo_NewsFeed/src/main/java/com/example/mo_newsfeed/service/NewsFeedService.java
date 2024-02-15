package com.example.mo_newsfeed.service;

import com.example.mo_newsfeed.client.ActivityClient;
import com.example.mo_newsfeed.client.UserClient;
import com.example.mo_newsfeed.newsFeed.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NewsFeedService {
    private final NewsFeedRepository newsFeedRepository;
    private final UserClient userClient;
    private final ActivityClient activityClient;

    public void create(NewsFeedDto newsFeedDto) {
        NewsFeed newsfeed = NewsFeed.builder()
                .userId(newsFeedDto.getUserId())
                .relatedUserId(newsFeedDto.getRelatedUserId())
                .activityId(newsFeedDto.getActivityId())
                .activityType(newsFeedDto.getActivityType())
                .build();
        newsFeedRepository.save(newsfeed);
    }
    public List<NewsFeedResDto> getUserNewsfeed(Long userId) {
        // 1. 사용자가 팔로우하는 모든 사용자의 ID를 가져옵니다.
        List<Long> followingIds = activityClient.findFollowingIds(userId);

        // 2. 해당 사용자들의 뉴스피드를 가져옵니다.
        List<NewsFeed> newsfeeds = newsFeedRepository.findByUserIdIn(followingIds);

        // 3. 가져온 뉴스피드를 NewsFeedResDto 객체로 변환하여 결과 리스트에 추가합니다.
        List<NewsFeedResDto> newsfeedResDtoList = newsfeeds.stream()
                .map(newsfeed -> new NewsFeedResDto(
                        newsfeed.getUserId(),
                        newsfeed.getRelatedUserId(),
                        newsfeed.getActivityId(),
                        newsfeed.getActivityType().name()))
                .collect(Collectors.toList());

        // 4. 최종 결과 리스트를 반환합니다.
        return newsfeedResDtoList;
    }

}

