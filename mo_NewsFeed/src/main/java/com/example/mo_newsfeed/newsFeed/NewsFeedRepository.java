package com.example.mo_newsfeed.newsFeed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {
    List<NewsFeed> findByUserIdIn(List<Long> userIds);
}