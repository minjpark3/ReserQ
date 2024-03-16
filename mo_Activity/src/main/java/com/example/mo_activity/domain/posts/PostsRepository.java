package com.example.mo_activity.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
//        List<Posts> findByUserId(Long userId);
        }