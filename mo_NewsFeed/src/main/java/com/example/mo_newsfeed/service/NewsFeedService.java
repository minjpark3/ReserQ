package com.example.mo_newsfeed.service;

import com.example.mo_newsfeed.client.ActivityClient;
import com.example.mo_newsfeed.client.UserClient;
import com.example.mo_newsfeed.newsFeed.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsFeedService {
    private final NewsFeedRepository newsFeedRepository;
    private final UserClient userClient;
    private final ActivityClient activityClient;

    @Transactional
    public void create(NewsFeedDto newsFeedDto) {
        NewsFeed newsfeed = NewsFeed.builder()
                .userId(newsFeedDto.getUserId())
                .type(newsFeedDto.getType())
                .activityId(newsFeedDto.getActivityId())
                .build();
        newsFeedRepository.save(newsfeed);
    }
    @Transactional
    public List<NewsFeedResDto> getUserNewsfeed(Long userId) {
        List<Long> followingIds = activityClient.findFollowingIds(userId);
        log.info("뉴스피드 조회 시작: " + followingIds);
        System.out.println("4");
        List<NewsFeed> newsfeedItems = newsFeedRepository.findByUserIdIn(followingIds);
        System.out.println("4");
        List<NewsFeedResDto> newsFeedRes = new ArrayList<>();
        for (NewsFeed newsFeed : newsfeedItems) {
            NewsFeedResDto newsFeedResDto = new NewsFeedResDto(
                    newsFeed.getUserId(),
                    newsFeed.getType().toString(),
                    newsFeed.getActivityId()
            );
            System.out.println("4");
            newsFeedRes.add(newsFeedResDto);
        }
        System.out.println("4");
        return newsFeedRes;
    }

}
