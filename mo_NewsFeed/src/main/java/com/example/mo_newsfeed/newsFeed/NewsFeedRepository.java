package com.example.mo_newsfeed.newsFeed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {
//    Page<NewsFeed> filterNewsfeeds(Long userId, List<Long> toUserIds, Pageable pageable);
//    @Query("SELECT nf FROM NewsFeed nf WHERE nf.userId = :userId OR nf.relatedUserId IN :toUserIds")
//    Page<NewsFeed> filterNewsfeeds(@Param("userId") Long userId, @Param("toUserIds") List<Long> toUserIdse);

    List<NewsFeed> findByUserIdIn(List<Long> userIds);
}